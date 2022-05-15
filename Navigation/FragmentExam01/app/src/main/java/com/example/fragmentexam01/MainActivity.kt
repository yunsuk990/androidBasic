package com.example.fragmentexam01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fragmentexam01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnFragment1.setOnClickListener {
            onReplaceFragment(FragmentFirst())
        }

        binding.btnFragment2.setOnClickListener {
            onReplaceFragment(FragmentSecond())
        }


    }

    fun onReplaceFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragmentContainerView, fragment).commit()
    }
}