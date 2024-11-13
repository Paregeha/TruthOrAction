package com.example.truthoraction.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.truthoraction.DataPlayers.AppDataBasePlayer
import com.example.truthoraction.R
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [LevelsQuestion::class], version = 1)
abstract class AppDataBaseQuestions : RoomDatabase() {
    abstract fun questionDao(): LevelsQuestionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBaseQuestions? = null

        fun getInstance(context: Context): AppDataBaseQuestions {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBaseQuestions::class.java,
                    "second_database"
                ).build().also { instance ->
                    INSTANCE = instance
                }
            }
        }

        // Заповнення бази даних даними з JSON-файлу в залежності від обраного рівня
        suspend fun prepopulateDatabase(context: Context, level: String) {
            val questionDao = getInstance(context).questionDao()
            val jsonResId = when (level) {
                "Beginner" -> R.raw.questions_beginner
                "Advanced" -> R.raw.questions_advanced
                "Expert" -> R.raw.questions_expert
                "Master" -> R.raw.questions_master
                else -> R.raw.questions_beginner
            }
            val json = context.resources.openRawResource(jsonResId).bufferedReader().use { it.readText() }
            val questions = parseJson(json)
            questionDao.insertQuestions(questions)
        }

        // Парсинг JSON
        private fun parseJson(json: String): List<LevelsQuestion> {
            val gson = Gson()
            val listType = object : TypeToken<List<LevelsQuestion>>() {}.type
            return gson.fromJson(json, listType)
        }
    }
}