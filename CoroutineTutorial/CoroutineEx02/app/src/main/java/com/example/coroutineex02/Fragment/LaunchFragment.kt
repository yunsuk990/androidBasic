package com.example.coroutineex02.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coroutineex02.databinding.FragmentButtonBinding
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

class LaunchFragment: Fragment(), CoroutineScope {
    companion object {
        const val TAG = "LaunchFragment"
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val dataProvider = DataProvider()

    private var _binding:FragmentButtonBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentButtonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLoad.setOnClickListener {
            loadDataScope()
        }
    }

    private fun loadDataScope() = launch(Dispatchers.IO) {
        println("My Job is $coroutineContext")
        println("1] I'm working in thread ${Thread.currentThread().name}")

        withContext(Dispatchers.Main){
            showLoading()
            println("2] I'm working in thread ${Thread.currentThread().name}")
        }
        println("3] I'm working in thread ${Thread.currentThread().name}")

        val result: String = dataProvider.loadData()
        println("4] I'm working in thread ${Thread.currentThread().name}, ${result}")

        withContext(Dispatchers.Main){
            println("5] I'm working in thread ${Thread.currentThread().name}")
            showText(result)
            hideLoading()
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showText(data:String) {
        binding.tvShow.text = data
    }

    class DataProvider() {
        suspend fun loadData(): String = withContext(Dispatchers.IO) {
            println("6] I'm working in thread ${Thread.currentThread().name}")
            delay(TimeUnit.SECONDS.toMillis(10))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}