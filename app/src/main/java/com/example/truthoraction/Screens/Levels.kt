package com.example.truthoraction.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.truthoraction.Data.QuestionsViewModel
import com.example.truthoraction.R

@Composable
fun Levels(viewModel: QuestionsViewModel) {
    val dataList = remember {
        mutableStateListOf("Beginner", "Advanced", "Expert", "Master")
    }
    var selectedLevel by remember { mutableStateOf<String?>(null) } // Використовуємо вибраний рівень з ViewModel

    val questionsState = viewModel.getQuestionsForLevel(selectedLevel).collectAsState(initial = emptyList())


    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    // Визначення висоти на основі ширини екрана
    val height = when {
        screenHeight > 700 -> 600.dp // Для великих екранів
        screenHeight > 600 -> 450.dp // Для середніх екранів
        else -> 300.dp // Для маленьких екранів
    }
    Box(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(
            modifier = Modifier.height(height)
        ) {
            itemsIndexed(dataList) { index, level ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(10.dp)
                        .clickable {
                            selectedLevel = level
                            viewModel.selectedLevel = level
                        }
                        .border(
                            width = 1.dp,
                            color = if (selectedLevel == level) Color.Green else Color.White,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(id = R.color.button)
                    )
                ) {
                    Row {
                        Text(
                            text = dataList[index],
                            modifier = Modifier.padding(5.dp),
                            color = colorResource(id = R.color.color_Text),
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily(Font(R.font.ibarra_real_nova_variable_font_wght))
                            )
                        )
                        when (dataList[index]) {
                            "Beginner" -> {
                                Image(
                                    painter = painterResource(id = R.drawable.beginnerimage),
                                    contentDescription = "BeginnerImage",
                                    modifier = Modifier
                                        .fillMaxSize()
                                )
                            }

                            "Advanced" -> {
//                                Image(
//                                    painter = painterResource(id = R.drawable.beginner_image),
//                                    contentDescription = "BeginnerImage",
//                                    modifier = Modifier
//                                        .fillMaxSize()
//                                )
                            }

                            "Expert" -> {
//                                Image(
//                                    painter = painterResource(id = R.drawable.beginner_image),
//                                    contentDescription = "BeginnerImage",
//                                    modifier = Modifier
//                                        .fillMaxSize()
//                                )
                            }

                            "Master" -> {
//                                Image(
//                                    painter = painterResource(id = R.drawable.beginner_image),
//                                    contentDescription = "BeginnerImage",
//                                    modifier = Modifier
//                                        .fillMaxSize()
//                                )
                            }
                        }
                    }
                }
            }
        }
    }
}