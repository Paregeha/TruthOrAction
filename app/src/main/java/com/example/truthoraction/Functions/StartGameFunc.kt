package com.example.truthoraction.Functions

import androidx.compose.runtime.Composable
import com.example.truthoraction.DataPlayers.PlayerViewModel
import com.example.truthoraction.DataPlayers.Players

fun StartGameFunc(
    playerViewModel: PlayerViewModel,
    players: List<Players>,
    currentIndex: Int,
    onNextPlayer: (Int) -> Unit
) {
    val nextIndex = (currentIndex + 1) % players.size // Циклічний вибір гравця
    onNextPlayer(nextIndex)
}