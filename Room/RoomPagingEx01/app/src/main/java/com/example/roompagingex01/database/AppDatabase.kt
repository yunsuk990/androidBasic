package com.example.roompagingex01.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/* AppDatabse 선언 - RoomDatabase() 상속
   - entities 어노테이션으로 데이터베이스에 포함된 엔티티 목록 선언
   - 데이터베이스의 구조가 바뀌면 Version 변경
*/
@Database(entities = arrayOf(NoteEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    /* DAO 클래스의 인스턴스를 반환하는 추상 메서드를 정의. */
    abstract fun noteDao(): NoteDao

    /* 데이터베이스 객체를 생성(Singleton)하여 반환하는 getDatabase() 함수 및
       변수들을 전역으로 선언 */
    companion object {
        // database 변수 선언
        private var database: AppDatabase? = null

        //database 이름 상수 선언
        private const val ROOM_DB = "note.db"

        /* 데이터베이스 객체를 싱글톤으로 생성하여 반환하는 함수 */
        fun getDatabase(context: Context): AppDatabase {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, ROOM_DB
                ).build()
            }
            /* 안전한 강제 캐스팅 */
            return database!!
        }
    }//end of companion object
}
