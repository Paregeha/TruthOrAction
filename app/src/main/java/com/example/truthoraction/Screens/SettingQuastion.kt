@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.example.truthoraction.Screens

import android.annotation.SuppressLint
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.truthoraction.Data.QuestionsViewModel
import com.example.truthoraction.R

@SuppressLint("AutoboxingStateCreation")
@Composable
fun SettingQuestion(navController: NavHostController,viewModel: QuestionsViewModel) {
    val tabs = listOf("Levels", "Custom")
    var selectedTabIndex by remember { mutableStateOf(0) }

    var colorFilter by remember { mutableStateOf<ColorFilter?>(null) }

    LaunchedEffect(viewModel.selectedLevel) {
        colorFilter = if (viewModel.selectedLevel == null) {
            ColorFilter.tint(
                color = Color.Gray,
                blendMode = BlendMode.Saturation
            )
        } else {
            null
        }
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
            modifier = Modifier
                .systemBarsPadding()
        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = Color.Transparent,
                contentColor = colorResource(id = R.color.color_Text),
                indicator = {tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = colorResource(id = R.color.color_text2), // Задаємо колір індикатора
                        height = 4.dp // Задаємо висоту індикатора
                    )
                }
            ) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = selectedTabIndex == index, // Змінюємо на tab
                        onClick = {
                            selectedTabIndex = index // Встановлюємо вибрану вкладку
                        },
                        text = {
                            Text(
                                text = tab,
                                fontWeight = FontWeight.Bold,
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.ibarra_real_nova_variable_font_wght)),
                                )
                            )
                        }
                    )
                }
            }

            // Додайте контент для вкладок
            when (selectedTabIndex) {
                0 -> {
                    Levels(viewModel)
                }
                1 -> {
                    // Вміст для вкладки Custom
                    Text(viewModel.selectedLevel.toString())
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
                    onClick = { navController.navigate("SettingPlayer") },
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
                    onClick = {
                        if (viewModel.selectedLevel != null)
                        navController.navigate("GameTable")
                              },
                    modifier = Modifier.size(150.dp, 100.dp),
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
