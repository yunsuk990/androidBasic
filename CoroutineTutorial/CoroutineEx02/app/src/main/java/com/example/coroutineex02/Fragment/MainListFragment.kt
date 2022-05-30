package com.example.coroutineex02.Fragment

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import com.example.coroutineex02.R
import java.lang.Exception

class MainListFragment: ListFragment() {
    companion object {
        const val TAG = "MainListFragment"

        private const val SAMPLE_LAUNCH = "1. 코루틴 launch"
        private const val SAMPLE_SEQUENTIALLY = "2. 코루틴 동기호출"
        private const val SAMPLE_PARALLEL = "1. 코루틴 비동기호출"
        private const val SAMPLE_EXCEPTION = "1. 코루틴 에러처리"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listData =  arrayListOf(
            SAMPLE_LAUNCH,
            SAMPLE_SEQUENTIALLY,
            SAMPLE_PARALLEL,
            SAMPLE_EXCEPTION
        )

        listAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, listData)
        listView.setOnItemClickListener{ _, _, position, _ ->
            when(listData[position]) {
                SAMPLE_LAUNCH -> showFragment(LaunchFragment(), LaunchFragment.TAG)
                SAMPLE_SEQUENTIALLY -> showFragment(LaunchSequentiallyFragment(), LaunchSequentiallyFragment.TAG)
                SAMPLE_PARALLEL -> showFragment(LaunchParallelFragment(), LaunchParallelFragment.TAG)
                SAMPLE_EXCEPTION -> showFragment(ExceptionFragment(), ExceptionFragment.TAG)

            }
        }
    }

    private fun showFragment(fragment: Fragment, tag: String){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }

}