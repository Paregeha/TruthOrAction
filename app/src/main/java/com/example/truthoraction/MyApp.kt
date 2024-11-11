package com.example.truthoraction
import android.app.Application
import com.example.truthoraction.Data.AppDataBaseQuestions

import com.example.truthoraction.DataPlayers.AppDataBasePlayer

class MyApp : Application() {
    val database by lazy { AppDataBasePlayer.getDatabase(this) }
    val database2 by lazy { AppDataBaseQuestions.getInstance(this) }
}