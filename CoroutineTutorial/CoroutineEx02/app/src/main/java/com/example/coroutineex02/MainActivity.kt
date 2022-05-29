package com.example.coroutineex02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coroutineex02.Fragment.MainListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, MainListFragment(), MainListFragment.TAG)
                .commit()
        }
    }

    override fun onBackPressed() {
        val isPopExcists = supportFragmentManager.popBackStackImmediate()
        if(!isPopExcists){
            super.onBackPressed()
        }
    }
}