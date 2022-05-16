package com.example.counterexam1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.counterexam1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG: String = "로그"
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewmodel by lazy {
        ViewModelProvider(this).get(CounterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val counterObserver = Observer<Int>{
            binding.counterTv.text = it.toString()
        }

        viewmodel.getCounter().observe(this, counterObserver)

//        code refactoring
//        viewmodel.getCounter().observe(this, Observer{
//            binding.counterTv.text = it.toString()
//        })

        init()
    }

    fun init() {
        with(binding){
            fabAdd.setOnClickListener {
                viewmodel.increaseOrDecreaseValue(1)
            }
            fabRemove.setOnClickListener {
                viewmodel.increaseOrDecreaseValue(-1)
            }
        }
    }
}