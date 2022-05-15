package com.example.loginapp01

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp01.databinding.GenreItemBinding
import com.example.loginapp01.databinding.MovieItemBinding
import java.lang.IllegalArgumentException


class MovieAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    // 아이템의 개수를 알려주는 getItemCount() 함수 재정의
    //[4]
    override fun getItemCount() = MovieData.values().size

    /* 각 아이템뷰에 맞는 viewType을 반환하는 getItemViewType() 함수 재정의
       - 매개변수로 전달된 position에 해당하는 viewType을 MovieData에서 가져와 반환하도록 함수 재정의
     */
    override fun getItemViewType(position: Int):Int{
        Log.d("RECYCLE", "position: ${position} - getItemViewType() 호출")
        return MovieData.values()[position].viewType
	//[5]
        

    }//end of getItemViewType

    /* viewType(FAMILY,DRAMA)에 따라, 뷰 홀더(GenreViewHolder, MovieViewHolder)를 각각 생성하여,
       반환하는 onCreateViewHolder() 함수 재정의
       - viewType은 getItemViewType(position: Int): Int에서 인자로 전달
   */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        //[6]
        when(viewType){
            FAMILY -> {
                return GenreViewHolder(GenreItemBinding.inflate(LayoutInflater.from(parent.context),
                    parent, false))
            }
            DRAMA -> {
                return MovieViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context),
                    parent, false))
            }
            else -> {
                throw IllegalArgumentException(Error("매칭되는 뷰타입이 없습니다"))
            }
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GenreViewHolder -> {
                holder.bindGenre(MovieData.values()[position].movieContent as GenreItem)
            }
            is MovieViewHolder -> {
                holder.bindMovie(MovieData.values()[position].movieContent as MovieItem)
            }
        }
    }

    //GENRE,MOVIE을 전역상수로 사용할 수 있도록 final static으로 선언
    companion object {
        const val FAMILY = 0
        const val DRAMA = 1
    }
}