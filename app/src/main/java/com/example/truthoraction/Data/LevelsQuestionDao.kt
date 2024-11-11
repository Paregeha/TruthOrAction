package com.example.truthoraction.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LevelsQuestionDao {
    @Insert
    suspend fun insertQuestions(questions: List<LevelsQuestion>)

    // Змінили метод на асинхронний Flow
    @Query("SELECT * FROM questions_level")
    fun getAllQuestions(): Flow<List<LevelsQuestion>>

    @Query("SELECT * FROM questions_level WHERE levels = :level")
    fun getQuestionsByLevel(level: String): Flow<List<LevelsQuestion>>
}