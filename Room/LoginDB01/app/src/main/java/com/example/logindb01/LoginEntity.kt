package com.example.logindb01

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity @Parcelize
data class LoginEntity(
    @PrimaryKey
    val userName: String,
    val userPass: String
    ): Parcelable
