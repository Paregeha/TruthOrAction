package com.example.truthoraction.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions_level")

data class LevelsQuestion(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val level: String,
    val questionText: String
)
