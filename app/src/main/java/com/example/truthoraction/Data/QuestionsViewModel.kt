package com.example.truthoraction.Data

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class QuestionsViewModel(private val questionDao: LevelsQuestionDao) : ViewModel() {
    var selectedLevel by mutableStateOf<String?>(null)
    var action by mutableStateOf<String?>(null)

    fun getQuestionsForLevel(level: String?): Flow<List<LevelsQuestion>> {
        return if (level != null) {
            questionDao.getQuestionsByLevel(level) // Отримуємо питання по рівню
        } else {
            flow { emit(emptyList<LevelsQuestion>()) }
        }.flowOn(Dispatchers.IO)
    }

}