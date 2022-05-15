package com.example.loginapp01

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//MovieContent interface
interface MovieContent {
    val genre: Int
}

//GenreItem data class 선언(MovieContent 인터페이스 구현)
data class GenreItem(
    override val genre: Int
) :  MovieContent

//MovieItem data class 선언(MovieContent 인터페이스 구현)
@Parcelize
data class MovieItem(
    override val genre: Int,
    val movie_imgRes: Int,
    val movie_title : String,
    val movie_director: String,
    val movie_grade: String,
) :  MovieContent, Parcelable

//MovieData enum class
enum class MovieData(val viewType: Int, val movieContent: MovieContent) {
    MOVIE1(MovieAdapter.FAMILY, GenreItem(0)),
    MOVIE2(MovieAdapter.DRAMA, MovieItem(0, R.drawable.m1, "레드슈즈", "홍성호", "전체")),
    MOVIE3(MovieAdapter.DRAMA, MovieItem(0, R.drawable.m2, "보스베이비2", "톰 맥그래스", "전체")),
    MOVIE4(MovieAdapter.FAMILY, GenreItem(1)),
    MOVIE5(MovieAdapter.DRAMA, MovieItem(1, R.drawable.m3, "히즈 올 댓", "로버트 이스코브", "15세")),
    MOVIE6(MovieAdapter.DRAMA, MovieItem(1, R.drawable.m4, "캘리포니아 크리스마스", "숀폴 피치니노", "12세"));
}
