package com.example.loginapp01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapp01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val mainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)


        /* RecyclerView의 Divider 구분선 넣기 */
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        mainBinding.rvMovie.addItemDecoration(divider)

        //리사이클러뷰에 어댑터 설정
        mainBinding.rvMovie.adapter = MovieAdapter()

        //리사이클러뷰에 레이아웃메니저 설정
        mainBinding.rvMovie.layoutManager = LinearLayoutManager(this)
    }
}