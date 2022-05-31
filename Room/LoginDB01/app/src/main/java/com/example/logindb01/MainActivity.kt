package com.example.logindb01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.logindb01.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.reflect.KProperty

class MainActivity : AppCompatActivity() {

    private val loginDao by lazy {
        LoginDatabase.getDatabase().logindao()
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            if(loginValidate()){
                lifecycleScope.launch(Dispatchers.Main){
                    withContext(Dispatchers.IO){
                        loginDao.insertUser(
                            LoginEntity(
                                userName = binding.editUsername.text.toString(),
                                userPass = binding.editPassword.text.toString()
                            )
                        )
                    }
                    clearTextField()
                    Toast.makeText(this@MainActivity, "유저 정보가 저장되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            if(loginValidate()){
                lifecycleScope.launch(Dispatchers.Main){
                    var userInfo: LoginEntity? = null
                    withContext(Dispatchers.IO){
                        userInfo = loginDao.selectUser(
                            userName = binding.editUsername.text.toString(),
                            userPass = binding.editPassword.text.toString()
                        )
                    }

                    userInfo?.let {
                        val mIntent = Intent(this@MainActivity, SecondActivity::class.java)
                        mIntent.putExtra(PARCABLE_USERINFO, it)
                        startActivity(mIntent)

                        clearTextField()
                    }?: kotlin.run {
                        clearTextField()
                        Toast.makeText(this@MainActivity, "존재하지 않는 유저정보입니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun loginValidate(): Boolean {
        if(binding.editUsername.text.toString().isBlank() || binding.editPassword.text.toString().isBlank()){
            Toast.makeText(this, "아이디나 패스워드를 입력하셔야합니다.", Toast.LENGTH_SHORT).show()
            return false
        }else{
            return true
        }
    }

    fun clearTextField() {
        binding.editUsername.setText("")
        binding.editPassword.setText("")
    }

    companion object {
        const val PARCABLE_USERINFO = "PARCABLE_USERINFO"
    }
}

