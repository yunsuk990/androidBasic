package com.example.fragmentexam02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.example.fragmentexam02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG:String = "로그"
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "MainActivity - onCreate() called")
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initBottomNav()
    }

    private fun initBottomNav() {
        binding.bottomNav.run{
            setOnItemSelectedListener { item ->
                when(item.itemId){
                    R.id.menu_home -> {
                        onReplaceFragment(HomeFragment())
                    }
                    R.id.menu_list -> {
                        onReplaceFragment(ListFragment())
                    }
                    R.id.menu_account -> {
                        onReplaceFragment(ProfileFragment())
                    }
                }
                true
            }
            selectedItemId = R.id.menu_home
        }
    }

    private fun onReplaceFragment(fragment: Fragment){
        with(supportFragmentManager.beginTransaction()){
            replace(R.id.framents_container, fragment)
        }.commit()
    }
}





















