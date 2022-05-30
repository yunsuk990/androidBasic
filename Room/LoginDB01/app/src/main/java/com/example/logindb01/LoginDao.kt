package com.example.logindb01

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LoginDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(loginEntity: LoginEntity)

    @Query("SELECT * FROM LoginEntity WHERE userName = :userName and userPass = :userPass")
    fun selectUser(userName: String, userPass: String): LoginEntity?
}