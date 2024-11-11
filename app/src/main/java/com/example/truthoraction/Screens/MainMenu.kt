package com.example.truthoraction.Screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.truthoraction.R

@Composable
fun MainMenu(navController: NavHostController) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background_theme),
                contentScale = ContentScale.Crop // Фон розтягується відповідно до екрана
            )
    ) {
        val isPortrait = maxHeight < 600.dp  // Визначаємо, чи екран у портретному режимі
        val buttonWidth = if (isPortrait) 200.dp else 300.dp  // Ширина кнопки залежить від екрана
        val buttonHeight = if (isPortrait) 70.dp else 90.dp  // Вища кнопка для великих екранів

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AdaptiveButton(
                text = "Start",
                width = buttonWidth,
                height = buttonHeight,
                onClick = { navController.navigate("SettingPlayer") }
            )

            Spacer(modifier = Modifier.size(if (isPortrait) 15.dp else 30.dp)) // Відстань змінюється

            AdaptiveButton(
                text = "Setting",
                width = buttonWidth,
                height = buttonHeight,
                onClick = { /* TODO */ }
            )
        }
    }
}

@Composable
fun AdaptiveButton(
    text: String,
    width: Dp,
    height: Dp,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .size(width, height)
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            ),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        onClick = onClick
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.ibarra_real_nova_variable_font_wght))
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainMenuPreview(){
    MainMenu(navController = rememberNavController())
}