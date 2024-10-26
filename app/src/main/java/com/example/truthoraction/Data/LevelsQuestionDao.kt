package com.example.truthoraction.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LevelsQuestionDao {
    @Query("SELECT * FROM questions_level WHERE level = :level")
    suspend fun getQuestionsByLevel(level: String): List<LevelsQuestion>

    @Insert
    suspend fun insertAll(vararg questions: LevelsQuestion)
}