package com.example.navigationex02

import androidx.annotation.DrawableRes

//FriendsData data 클래스
data class FriendsData(
    val firendName: String,
    val enlgishName: String,
    @DrawableRes val imageResource: Int,
    val friendRank: Int
)
/* @DrawableRes 애너테이션
   - @DrawableRes 애너테이션은 리소스 아이디 관련 애너테이션으로
    속성 imageResource의 값이 이 애너테이션이 의미하는 리소스 타입에 해당하는 리소스의 아이디임을 의미
    즉, imageResource의 값이 Int 타입 리소스임을 알려줌
    (Android의 모든 리소스는 R 클래스에 의해 Int 타입의 아이디로 관리)
 */