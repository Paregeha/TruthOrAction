package com.example.truthoraction

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.truthoraction.Screens.GameTable
import com.example.truthoraction.Screens.MainMenu
import com.example.truthoraction.Screens.SettingPlayer
import com.example.truthoraction.Screens.SettingQuestion
import com.example.truthoraction.Screens.SettingRounds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "MainMenu") {
                composable("MainMenu") { MainMenu(navController) }
                composable("SettingPlayer") { SettingPlayer(navController) }
                composable("SettingQuestion") { SettingQuestion(navController) }
                composable("SettingRounds") { SettingRounds(navController) }
                composable("GameTable") { GameTable(navController) }

            }
        }
    }
}