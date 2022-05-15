package com.example.loginapp01

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.loginapp01.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {
    private val movieBinding by lazy {
        ActivityMovieBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(movieBinding.root)

        //인텐트에서 "SINGER"라는 이름으로 전달된 가수의 상수명을 문자열로 추출
        val receiveMovie: MovieItem? = intent.getParcelableExtra<MovieItem>(MOVIE)

        receiveMovie?.let {
            Log.i("MOVIE_NAME", it.movie_title)
            //해당 학생의 정보를 상세보기 레이아웃(activity_student.xml)에 바인딩
            movieBinding.imgMoviePhoto.setImageResource(it.movie_imgRes)
            movieBinding.tvMovieTitle.text = it.movie_title
            movieBinding.tvMoveDirector.text = it.movie_director
        }?: run {
            Toast.makeText(this, "MovieItem이 null입니다.", Toast.LENGTH_LONG).show()
        }
/*
        movieBinding.btnInfo.setOnClickListener {
            //인텐트를 생성하고 액션은 전화다이얼을 입력해주는 ACTION_DIAL 지정

            /* 인텐트에 데이터 지정
               - "tel:" : 전화번호를 나타내는 국제표준 Uri
               - Uri.parse() : 입력한 전화번호를 가져와서 Uri 객체로 파싱하여 인텐트 data에 설정
            */

            Intent(Intent.ACTION_VIEW, Uri.parse("http://" + receiveMovie?.movie_url)).let {

               if (it.resolveActivity(packageManager) != null) {
                    startActivity(it)
               }
            }

            /* intent.resolveActivity(packageManager) 메서드로,
               이 인텐트를 수행하는 액티비티가 있는지 검사하여 반환,
               즉 전화걸기 앱이 있는지 검사하여 있으면 전화걸기 앱 호출
            */

        }
*/
        movieBinding.btnHome.setOnClickListener {
            finish()
        }
    }

    //"MOVIE_ITEM" 전역상수 선언
    companion object {
        const val MOVIE = "MOVIE"
    }

}