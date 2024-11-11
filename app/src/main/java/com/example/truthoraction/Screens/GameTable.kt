package com.example.truthoraction.Screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.truthoraction.Data.QuestionsViewModel
import com.example.truthoraction.DataPlayers.PlayerViewModel
import com.example.truthoraction.DataPlayers.Players
import com.example.truthoraction.Functions.StartGameFunc
import com.example.truthoraction.R

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun GameTable(
    navController: NavHostController,
    playerViewModel: PlayerViewModel,
    questionsViewModel: QuestionsViewModel
) {

    val players = playerViewModel.players.value

    var isButtonVisible by remember { mutableStateOf(true) }

    // Поділ гравців на верхній і нижній ряд
    val topRowPlayers = players.take((players.size + 1) / 2) // Верхній ряд
    val bottomRowPlayers = players.drop((players.size + 1) / 2) // Нижній ряд

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val screenHeightImage = configuration.screenHeightDp

    val imageNoteSize = when {
        screenHeightImage > 700 -> 0.8f // Для великих екранів
        screenHeightImage > 600 -> 0.65f // Для середніх екранів
        else -> 0.6f // Для маленьких екранів
    }

    var selectedPlayerIndex by remember { mutableStateOf(-1) }

    var isTruthOrActionVisible by remember { mutableStateOf(false) }

    val questionsState by questionsViewModel.getQuestionsForLevel(questionsViewModel.selectedLevel)
        .collectAsState(initial = emptyList())

    var isQuestionTruthOrActionVisible by remember { mutableStateOf(false) }

    var isTruthOrActionInfo by remember { mutableStateOf("") }

    var question by remember { mutableStateOf("") }

    var isButtonNextVisible by remember { mutableStateOf(false) }

    var isRefusePerform by remember { mutableStateOf(false) }

    var isAlcohol by remember { mutableStateOf(false) }

    var isDone by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_theme),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    // Висота, адаптована до екрана
                    .height(screenHeight * 0.25f) // Наприклад, 20% від висоти екрана
            ) {
                PlayerRowTop(players = topRowPlayers, selectedPlayerIndex, players) // Верхній ряд
            }
            Box(
                contentAlignment = Alignment.Center
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.main_note),
                    contentDescription = "Note",
                    modifier = Modifier
                        .size(screenWidth * imageNoteSize)
                        .clipToBounds(),
                    contentScale = ContentScale.FillBounds // Збільшує зображення до контейнера
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (isQuestionTruthOrActionVisible) {
                        Text(
                            text = question,
                            style = TextStyle(
                                fontSize = (screenHeight.value * 0.03).sp, // 2.5% від висоти екрана для розміру шрифту
                                fontFamily = FontFamily(Font(R.font.ibarra_real_nova_variable_font_wght)),
                                color = colorResource(id = R.color.button)
                            )
                        )
                    }

                    if (isButtonVisible) {
                        Text(
                            text = "Press start to play",
                            style = TextStyle(
                                fontSize = (screenHeight.value * 0.03).sp, // 2.5% від висоти екрана для розміру шрифту
                                fontFamily = FontFamily(Font(R.font.ibarra_real_nova_variable_font_wght)),
                                color = colorResource(id = R.color.button)
                            )
                        )


                        Spacer(modifier = Modifier.padding(5.dp))

                        IconButton(
                            onClick = {
                                StartGameFunc(
                                    playerViewModel,
                                    players,
                                    selectedPlayerIndex
                                ) { newIndex ->
                                    selectedPlayerIndex = newIndex // Змінюємо обраного гравця
                                    isTruthOrActionVisible = true
                                }
                                isButtonVisible = false // Приховуємо кнопку після натискання
                            },
                            modifier = Modifier.size(150.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.button_start),
                                contentDescription = "button",

                                )
                            Text(
                                text = "Start",
                                style = TextStyle(
                                    fontSize = (screenHeight.value * 0.045).sp, // 2.5% від висоти екрана для розміру шрифту
                                    fontFamily = FontFamily(Font(R.font.ibarra_real_nova_variable_font_wght)),
                                    color = colorResource(id = R.color.color_Text)
                                )
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight * 0.25f), // Наприклад, 20% від висоти екрана
                contentAlignment = Alignment.TopCenter
            )
            {
                PlayerRowBottom(
                    players = bottomRowPlayers,
                    selectedPlayerIndex,
                    players
                ) // Нижній ряд
            }
            if (isButtonNextVisible) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(WindowInsets.navigationBars.asPaddingValues()) // Відступ для навігаційних кнопок

                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        IconButton(
                            onClick = {
                                StartGameFunc(
                                    playerViewModel,
                                    players,
                                    selectedPlayerIndex
                                ) { newIndex ->
                                    selectedPlayerIndex = newIndex // Змінюємо обраного гравця
                                    isTruthOrActionVisible = true
                                }
                                isQuestionTruthOrActionVisible = false
                                isButtonNextVisible = false
                            },
                            modifier = Modifier.size(150.dp, 100.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.cancel_confirm),
                                contentDescription = "Next",
                                modifier = Modifier.padding(5.dp)
                            )
                            Text(
                                "Next",
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

            if (isDone) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(WindowInsets.navigationBars.asPaddingValues()) // Відступ для навігаційних кнопок

                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        IconButton(
                            onClick = {
                                StartGameFunc(
                                    playerViewModel,
                                    players,
                                    selectedPlayerIndex
                                ) { newIndex ->
                                    selectedPlayerIndex = newIndex // Змінюємо обраного гравця
                                    isTruthOrActionVisible = true
                                }
                                isDone = false
                                isQuestionTruthOrActionVisible = false

                            },
                            modifier = Modifier.size(150.dp, 100.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.cancel_confirm),
                                contentDescription = "Done",
                                modifier = Modifier.padding(5.dp)
                            )
                            Text(
                                "Done",
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

            if (isRefusePerform) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(WindowInsets.navigationBars.asPaddingValues()) // Відступ для навігаційних кнопок

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        IconButton(
                            onClick = {
                                StartGameFunc(
                                    playerViewModel,
                                    players,
                                    selectedPlayerIndex
                                ) { newIndex ->
                                    selectedPlayerIndex = newIndex // Змінюємо обраного гравця
                                    isTruthOrActionVisible = true
                                }
                                isDone = false
                                isQuestionTruthOrActionVisible = false
                                isRefusePerform = false
                            },
                            modifier = Modifier.size(150.dp, 100.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.cancel_confirm),
                                contentDescription = "Refuse",
                                modifier = Modifier.padding(5.dp)
                            )
                            Text(
                                "Refuse",
                                color = colorResource(id = R.color.color_Text),
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontFamily = FontFamily(Font(R.font.ibarra_real_nova_variable_font_wght))
                                )
                            )
                        }


                        IconButton(
                            onClick = {
                                isDone = true
                                isRefusePerform = false
                            },
                            modifier = Modifier.size(150.dp, 100.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.cancel_confirm),
                                contentDescription = "Perform",
                                modifier = Modifier.padding(5.dp)
                            )

                            Text(
                                "Perform",
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

            if (isTruthOrActionVisible) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(WindowInsets.navigationBars.asPaddingValues()) // Відступ для навігаційних кнопок

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        IconButton(
                            onClick = {
                                question = questionsState.filter { it.truthOrAction == "Truth" }
                                    .randomOrNull()?.question.toString()
                                isQuestionTruthOrActionVisible = true
                                isButtonNextVisible = true
                                isTruthOrActionVisible = false

                            },
                            modifier = Modifier.size(150.dp, 100.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.cancel_confirm),
                                contentDescription = "Truth",
                                modifier = Modifier.padding(5.dp)
                            )
                            Text(
                                "Truth",
                                color = colorResource(id = R.color.color_Text),
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontFamily = FontFamily(Font(R.font.ibarra_real_nova_variable_font_wght))
                                )
                            )
                        }
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                "OR",
                                color = colorResource(id = R.color.color_Text),
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontFamily = FontFamily(Font(R.font.ibarra_real_nova_variable_font_wght))
                                )
                            )
                        }

                        IconButton(
                            onClick = {
                                question = questionsState.filter { it.truthOrAction == "Action" }
                                    .randomOrNull()?.question.toString()
                                isQuestionTruthOrActionVisible = true
                                isTruthOrActionVisible = false
                                isRefusePerform = true
                            },
                            modifier = Modifier.size(150.dp, 100.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.cancel_confirm),
                                contentDescription = "Action",
                                modifier = Modifier.padding(5.dp)
                            )

                            Text(
                                "Action",
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
    }
}

@Composable
fun PlayerRowTop(players: List<Players>, selectedPlayerIndex: Int, playersAll: List<Players>) {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(screenHeight * 0.3f), // Наприклад, 20% від висоти екрана
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Top
    ) {
        players.forEachIndexed() { index, player ->
            if (players.size == 3 && index == 1) {
                Column(
                    verticalArrangement = Arrangement.Top,
                ) {
                    PlayerCard(player, isHighlighted = (index == selectedPlayerIndex))
                }
            } else {
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    PlayerCard(player, isHighlighted = (index == selectedPlayerIndex))
                }
            }
        }
    }
}

@Composable
fun PlayerRowBottom(players: List<Players>, selectedPlayerIndex: Int, playersAll: List<Players>) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(screenHeight * 0.3f), // Наприклад, 20% від висоти екрана
        horizontalArrangement = Arrangement.SpaceAround // Рівномірний розподіл
    ) {
        players.forEachIndexed() { index, player ->
            if (players.size == 3 && index == 1) {
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    when {
                        playersAll.size == 2 -> PlayerCard(
                            player,
                            isHighlighted = (index == selectedPlayerIndex - 1)
                        )

                        playersAll.size == 3 -> PlayerCard(
                            player,
                            isHighlighted = (index == selectedPlayerIndex - 2)
                        )

                        playersAll.size == 4 -> PlayerCard(
                            player,
                            isHighlighted = (index == selectedPlayerIndex - 2)
                        )

                        playersAll.size == 5 -> PlayerCard(
                            player,
                            isHighlighted = (index == selectedPlayerIndex - 3)
                        )

                        playersAll.size == 6 -> PlayerCard(
                            player,
                            isHighlighted = (index == selectedPlayerIndex - 3)
                        )
                    }

                }
            } else {
                Column(
                    verticalArrangement = Arrangement.Top,
                ) {
                    when {
                        playersAll.size == 2 -> PlayerCard(
                            player,
                            isHighlighted = (index == selectedPlayerIndex - 1)
                        )

                        playersAll.size == 3 -> PlayerCard(
                            player,
                            isHighlighted = (index == selectedPlayerIndex - 2)
                        )

                        playersAll.size == 4 -> PlayerCard(
                            player,
                            isHighlighted = (index == selectedPlayerIndex - 2)
                        )

                        playersAll.size == 5 -> PlayerCard(
                            player,
                            isHighlighted = (index == selectedPlayerIndex - 3)
                        )

                        playersAll.size == 6 -> PlayerCard(
                            player,
                            isHighlighted = (index == selectedPlayerIndex - 3)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PlayerCard(player: Players, isHighlighted: Boolean) {
    val iconSize = if (isHighlighted) 0.125f else 0.1f

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Box(
        modifier = Modifier
            .height(screenHeight * 0.2f) // 15% від висоти екрана для адаптивної висоти картки ,
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(120.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = player.icon),
                contentDescription = "Player Icon",
                modifier = Modifier
                    .size(screenHeight * iconSize) // 10% від висоти екрана для розміру зображення
            )
            Text(
                text = player.name,
                color = colorResource(id = R.color.color_Text),
                style = TextStyle(
                    fontSize = (screenHeight.value * 0.025).sp, // 2.5% від висоти екрана для розміру шрифту
                    fontFamily = FontFamily(Font(R.font.ibarra_real_nova_variable_font_wght)),
                )
            )
        }
    }
}
