package com.example.coroutineex02.Fragment

import androidx.fragment.app.ListFragment

class MainListFragment: ListFragment() {
    companion object {
        const val TAG = "MainListFragment"

        private const val SAMPLE_LAUNCH = "1. 코루틴 launch"
        private const val SAMPLE_SEQUENTIALLY = "2. 코루틴 동기호출"
        private const val SAMPLE_PARALLEL = "1. 코루틴 비동기호출"
        private const val SAMPLE_EXCEPTION = "1. 코루틴 에러처리"
    }

}