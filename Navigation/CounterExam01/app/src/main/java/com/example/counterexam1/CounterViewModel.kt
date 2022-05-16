package com.example.counterexam1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {

    private val counterML = MutableLiveData<Int>().apply{
        Log.d(MainActivity.TAG, "CounterViewModel 초기화")
        setValue(0)
    }

    fun getCounter(): LiveData<Int> = counterML

    fun increaseOrDecreaseValue(amount: Int){
        Log.d(MainActivity.TAG, "increaseOrDecreaseValue() called - LiveData 변경 발생")
        val origin = counterML.value ?: 0
        counterML.postValue(origin+amount)
    }



}