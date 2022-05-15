package com.example.loginapp01

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp01.MovieActivity.Companion.MOVIE
import com.example.loginapp01.MovieAdapter.Companion.DRAMA
import com.example.loginapp01.MovieAdapter.Companion.FAMILY
import com.example.loginapp01.databinding.GenreItemBinding
import com.example.loginapp01.databinding.MovieItemBinding

/**
 * GenreViewHolder 클래스 선언
 */
class GenreViewHolder(private val gBinding: GenreItemBinding) : RecyclerView.ViewHolder(gBinding.root) {

    //GenreItem 아이템뷰(genre_item.xml) 데이터 바인딩 함수
    fun bindGenre(genreItem: GenreItem) {
        Log.d(TAG, "GenreViewHolder 클래스의 bindGenre() 호출")
	//[1]
        when(genreItem.genre){
            FAMILY -> {
                gBinding.tvGenre.text = "가족"
            }
            DRAMA -> {
                gBinding.tvGenre.text = "드라마"
            }
        }
    }
}
/**
 *  MovieViewHolderr 클래스 선언
 */
class MovieViewHolder(private val mBinding: MovieItemBinding) : RecyclerView.ViewHolder(mBinding.root) {

    //MovieItem 아이템뷰(movie_item.xml) 데이터 바인딩 함수
    fun bindMovie(movieItem: MovieItem) {
    //[2]
        mBinding.imgPhoto.setImageResource(movieItem.movie_imgRes)
        mBinding.tvTitle.text = movieItem.movie_title
        mBinding.tvDirector.text = movieItem.movie_director
        mBinding.tvGrade.text = movieItem.movie_grade

        //리사이클러뷰에서 ViewHolder의 itemView를 클릭하면 상세보기 화면 생성(MovieActivity)
        itemView.setOnClickListener {
	    //[3]
            var sintent = Intent(itemView.context, MovieActivity::class.java)?.let {
                it.putExtra(MOVIE, movieItem )
                itemView.context.startActivity(it)
            }
        }
    }
}
