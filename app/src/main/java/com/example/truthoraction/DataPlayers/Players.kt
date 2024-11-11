package com.example.truthoraction.DataPlayers

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Players")
data class Players(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var name: String,
    var gender: String,
    var icon: Int
)