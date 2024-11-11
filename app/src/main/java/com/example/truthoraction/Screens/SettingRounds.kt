@file:Suppress("UNUSED_EXPRESSION")

package com.example.truthoraction.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.truthoraction.R

@Composable
fun SettingRounds(navController: NavHostController) {
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





            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Bottom
            ) {
                IconButton(
                    onClick = { navController.navigate("SettingQuestion") },
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
                    onClick = { navController.navigate("GameTable") },
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

@Preview(showBackground = true)
@Composable
fun PreviewSettingRounds() {
    SettingRounds(navController = rememberNavController())
}