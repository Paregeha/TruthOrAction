package com.example.truthoraction

import ClearDatabaseWorker
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.truthoraction.DataPlayers.AppDataBasePlayer
import com.example.truthoraction.DataPlayers.PlayerViewModel
import com.example.truthoraction.Screens.GameTable
import com.example.truthoraction.Screens.MainMenu
import com.example.truthoraction.Screens.SettingPlayer
import com.example.truthoraction.Screens.SettingQuestion
import com.example.truthoraction.Screens.SettingRounds

class MainActivity : ComponentActivity() {
    private val db: AppDataBasePlayer by lazy { (application as MyApp).database }
    private val playerViewModel: PlayerViewModel by lazy { PlayerViewModel(db.playerDao()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "MainMenu") {
                composable("MainMenu") { MainMenu(navController) }
                composable("SettingPlayer") { SettingPlayer(navController, playerViewModel) }
                composable("SettingQuestion") { SettingQuestion(navController) }
                composable("SettingRounds") { SettingRounds(navController) }
                composable("GameTable") { GameTable(navController) }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        val clearDbWorkRequest = OneTimeWorkRequestBuilder<ClearDatabaseWorker>().build()
        WorkManager.getInstance(this).enqueue(clearDbWorkRequest)
    }
}