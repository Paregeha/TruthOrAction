package com.example.truthoraction.Data

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class QuestionsViewModel(
    private val questionDao: LevelsQuestionDao,
    private val context: Context
) : ViewModel() {

    var selectedLevel: String? = null
        set(value) {
            field = value
            viewModelScope.launch {
                value?.let { level ->
                    // Викликаємо prepopulateDatabase із відповідним рівнем
                    questionDao.deleteQuestionsByLevel()
                    AppDataBaseQuestions.prepopulateDatabase(context, level)
                }
            }
        }

    var action by mutableStateOf<String?>(null)

    fun getQuestionsForLevel(level: String?): Flow<List<LevelsQuestion>> {
        return if (level != null) {
            questionDao.getQuestionsByLevel(level)
        } else {
            flow { emit(emptyList<LevelsQuestion>()) }
        }.flowOn(Dispatchers.IO)
    }
}