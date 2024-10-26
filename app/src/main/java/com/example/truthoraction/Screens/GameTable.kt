package com.example.truthoraction.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun GameTable(navController: NavHostController) {
    Box(

    ){

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGameTable(){
    GameTable(navController = rememberNavController())
}