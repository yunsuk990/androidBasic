package com.example.logindb01

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(LoginEntity::class), version = 1)
abstract class LoginDatabase: RoomDatabase() {

    abstract fun logindao(): LoginDao

    companion object {
        private var database: LoginDatabase? = null
        private const val ROOM_DB = "room.db"
    }

    fun getDatabase(context: Context):LoginDatabase {
        if(database == null){
            database = Room.databaseBuilder(
                context.applicationContext,
                LoginDatabase::class.java,
                ROOM_DB
            ).build()
        }
        return database!!
    }


}