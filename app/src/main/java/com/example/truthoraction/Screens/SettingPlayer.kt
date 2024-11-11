package com.example.truthoraction.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.truthoraction.DataPlayers.PlayerDao
import com.example.truthoraction.DataPlayers.PlayerViewModel
import com.example.truthoraction.DataPlayers.Players
import com.example.truthoraction.R

@Composable
fun SettingPlayer(navController: NavHostController, playerViewModel: PlayerViewModel) {
    val dataList = remember {
        mutableStateListOf<Players>()
    }
    val players by playerViewModel.players.collectAsState()
    var showDialog by remember {
        mutableStateOf(false)
    }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    // Визначення висоти на основі ширини екрана
    val height = when {
        screenHeight > 700 -> 600.dp // Для великих екранів
        screenHeight > 600 && players.size != 6 -> 400.dp // Для середніх екранів
        screenHeight > 600 -> 500.dp

        else -> 300.dp // Для маленьких екранів
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_theme),
            contentDescription = null, // Вкажіть опис для доступності
            contentScale = ContentScale.Crop, // Адаптивне масштабування зображення
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.systemBarsPadding()
        ) {
            if (showDialog) {
                SettingPlayerDialog(
                    onDismiss = { showDialog = false },
                    onConfirm = { name, gender, icon ->
                        playerViewModel.addPlayer(Players(id = 0, name, gender, icon))
                        showDialog = false
                    },
                    dataIcons = listOf(
                        R.drawable.beautygirl1_1,
                        R.drawable.beautygirl1_2,
                        R.drawable.beautygirl1_3
                    )
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                        then (
                        if (screenWidth <= 600) {
                            Modifier.heightIn(
                                max = height
                            )
                        } else {
                            Modifier // без heightIn для ширших екранів
                        }
                        )
            ) {
                items(playerViewModel.players.value) { playersData ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                            .border(
                                width = 1.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.button)
                        ),

                        ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(0.5f)
                            ) {
                                Text(
                                    text = playersData.name,
                                    color = colorResource(id = R.color.color_Text),
                                    style = TextStyle(
                                        fontSize = 20.sp,
                                        fontFamily = FontFamily(Font(R.font.ibarra_real_nova_variable_font_wght))
                                    )
                                )
                                Text(text = screenHeight.toString())
                            }

                            Column(
                                modifier = Modifier.fillMaxWidth(0.5f),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = playersData.gender,
                                    color = colorResource(id = R.color.color_Text),
                                    style = TextStyle(
                                        fontSize = 20.sp,
                                        fontFamily = FontFamily(Font(R.font.ibarra_real_nova_variable_font_wght))
                                    )
                                )
                            }

                            Column(
                                horizontalAlignment = AbsoluteAlignment.Right
                            ) {
                                Image(
                                    painter = painterResource(id = playersData.icon),
                                    contentDescription = "Icon",
                                    modifier = Modifier.size(64.dp)
                                )
                            }
                            Column {
                                IconButton(onClick = { playerViewModel.removePlayer(playersData) }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete",
                                        tint = colorResource(id = R.color.color_text2)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            if (players.size < 6) {
                Image(
                    painter = painterResource(id = R.drawable.line),
                    contentDescription = "Line",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp, start = 10.dp, end = 10.dp, bottom = 10.dp),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = { showDialog = true },
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.plus_setting_player),
                            contentDescription = "AddPlus",
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Bottom
            ) {
                IconButton(
                    onClick = {
                        navController.navigate("MainMenu")
                        playerViewModel.removeAllPlayer()
                    },
                    modifier = Modifier.size(150.dp, 100.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cancel_confirm),
                        contentDescription = "Cancel",
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        "Cancel",
                        color = colorResource(id = R.color.color_Text),
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.ibarra_real_nova_variable_font_wght))
                        )
                    )
                }

                IconButton(
                    onClick = { navController.navigate("SettingQuestion") },
                    modifier = Modifier.size(150.dp, 100.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cancel_confirm),
                        contentDescription = "Confirm",
                        modifier = Modifier.padding(5.dp)
                    )

                    Text(
                        "Confirm",
                        color = colorResource(id = R.color.color_Text),
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.ibarra_real_nova_variable_font_wght))
                        )
                    )
                }
            }
        }
    }
}

