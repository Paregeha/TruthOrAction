package com.example.truthoraction.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.RectangleShape
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
fun MainMenu(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(id = R.drawable.background_theme))
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier
                    .size(200.dp, 70.dp)
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    ),
                shape = RoundedCornerShape(10.dp),
                onClick = { navController.navigate("SettingPlayer") },
                colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                Text(
                    text = "Start",
                    fontSize = 24.sp,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.ibarra_real_nova_variable_font_wght))
                    )
                )
            }

            Spacer(modifier = Modifier.size(15.dp))

            Button(
                modifier = Modifier
                    .size(200.dp, 70.dp)
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    ),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = "Setting",
                    fontSize = 24.sp,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.ibarra_real_nova_variable_font_wght))
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainMenuPreview(){
    MainMenu(navController = rememberNavController())
}