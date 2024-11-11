package com.example.truthoraction.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions_level")

data class LevelsQuestion(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val levels: String,
    val truthOrAction: String,
    val question: String,
    val custom: String
)
