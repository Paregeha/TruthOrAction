package com.example.truthoraction.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.truthoraction.DataPlayers.AppDataBasePlayer
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
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBaseQuestions::class.java,
                    "second_database"
                )
                    .addCallback(PrepopulateCallback(context)) // Додаємо колбек для початкових даних
                    .build()
                INSTANCE = instance
                instance
            }
        }

        // Колбек для початкових даних
        private class PrepopulateCallback(
            private val context: Context
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.IO).launch {
                    getInstance(context).questionDao().insertQuestions(prepopulateDataBeginner())
                }
            }
        }

        private fun prepopulateDataBeginner(): List<LevelsQuestion> {
            return listOf(
                LevelsQuestion(
                    levels = "Beginner",
                    truthOrAction = "Truth",
                    question = "Beginner-Truth",
                    custom = ""
                ),
                LevelsQuestion(
                    levels = "Beginner",
                    truthOrAction = "Action",
                    question = "Beginner-Action",
                    custom = ""
                ),
                LevelsQuestion(
                    levels = "Advanced",
                    truthOrAction = "Truth",
                    question = "Advanced1-Truth",
                    custom = ""
                ),
                LevelsQuestion(
                    levels = "Advanced",
                    truthOrAction = "Action",
                    question = "Advanced-Action!",
                    custom = ""
                ),
                // Додайте більше питань за потреби
            )
        }

    }
}