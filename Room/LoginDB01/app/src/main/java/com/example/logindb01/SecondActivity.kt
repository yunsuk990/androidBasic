package com.example.logindb01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.logindb01.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<LoginEntity>(MainActivity.PARCABLE_USERINFO)

        if(user != null){
            Log.i("USER-DATA", "전달받은 유저정보는 ${user.userName} &{user.userPass} 입니다.")
        }

        if(user != null){
            binding.tvUsername.text = "userName: ${user.userName}"
        }

        binding.btnFinish.setOnClickListener {
            finish()
        }
    }
}