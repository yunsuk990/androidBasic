package com.example.roompagingex01.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/* @Entity 정의 */
@Entity(tableName = "NoteEntity")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    var noteIdx: Int? = null,
    val noteContent: String
)

