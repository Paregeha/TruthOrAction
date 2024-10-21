package com.example.truthoraction.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.truthoraction.Data.PlayersData
import com.example.truthoraction.R

@Composable
fun SettingPlayer(navController: NavHostController) {
    var dataList = remember {
        mutableStateListOf<PlayersData>()
    }
    var showDialog by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(id = R.drawable.background_theme))
    ) {
        Column(
            modifier = Modifier.systemBarsPadding()

        ) {
            if (showDialog) {
                SettingPlayerDialog(
                    onDismiss = { showDialog = false },
                    onConfirm = { name, gender, icon ->
                        dataList.add(PlayersData(name, gender,icon))
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
            ) {

                items(dataList) { playersData ->
                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = playersData.name)
                            Text(text = playersData.gender)
                            Image(
                                painter = painterResource(id = playersData.icon),
                                contentDescription = "Icon",
                                modifier = Modifier.size(64.dp)
                            )

                        }
                    }
                }
            }
            Image(
                painter = painterResource(id = R.drawable.line),
                contentDescription = "Line",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 10.dp),
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

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Bottom
            ) {
                IconButton(
                    onClick = { navController.navigate("MainMenu") },
                    modifier = Modifier.size(120.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cancel_confirm),
                        contentDescription = "Cancel",
                        modifier = Modifier.padding(5.dp)
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.size(120.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cancel_confirm),
                        contentDescription = "Confirm",
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingPlayerPreview() {
    SettingPlayer(navController = rememberNavController())
}