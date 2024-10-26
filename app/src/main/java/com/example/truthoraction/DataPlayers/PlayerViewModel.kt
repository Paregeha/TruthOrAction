package com.example.truthoraction.DataPlayers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlayerViewModel(private val dao:PlayerDao):ViewModel(){
    private val _players = MutableStateFlow<List<Players>>(emptyList())
    val players: StateFlow<List<Players>> = _players.asStateFlow()

    init {
        loadPlayers()
    }

    private fun loadPlayers() {
        viewModelScope.launch(Dispatchers.IO) {
            _players.value = dao.getAllPlayers()
        }
    }

    fun addPlayer(player: Players) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertPlayer(player)
            loadPlayers() // Оновлюємо список
        }
    }

    fun removePlayer(player: Players) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deletePlayer(player)
            loadPlayers() // Оновлюємо список
        }
    }

    fun removeAllPlayer(){
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteAllPlayers()
            loadPlayers()
        }
    }
}