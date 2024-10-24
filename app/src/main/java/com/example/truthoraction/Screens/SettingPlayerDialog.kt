@file:Suppress("UNUSED_EXPRESSION")

package com.example.truthoraction.Screens

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.truthoraction.R

@SuppressLint("ResourceAsColor")
@Composable
fun SettingPlayerDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String, Int) -> Unit, // Змінили тип selectedIcon на Int для ID ресурсу
    dataIcons: List<Int>
) {
    var name by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("") } // За замовчуванням обрано Male
    var selectedIcon by remember { mutableStateOf<Int?>(null) } // Зберігаємо ID вибраної іконки


    val dataIconsFemale = remember {
        mutableStateListOf(
            R.drawable.beautygirl1_1,
            R.drawable.beautygirl1_2,
            R.drawable.beautygirl1_3
        )
    }
    val dataIconsMale = remember {
        mutableStateListOf(
            R.drawable.beautygirl1_1,
        )
    }

    Dialog(
        onDismissRequest = onDismiss
    ) {
        // Box для кастомного фону та оформлення
        Box(
            modifier = Modifier
                .fillMaxWidth()
//                .clip(RoundedCornerShape(16.dp)) // Заокруглені кути
//                .background(Color.Transparent) // Прозорий фон
                .border(
                    width = 1.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(5.dp)
                )
                .paint(painter = painterResource(id = R.drawable.theme_dialog)) // Фонове зображення
//                .padding(24.dp) // Збільшені внутрішні відступи для простору
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp) // Відступи між компонентами
            ) {
                // Поле для введення імені
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = {
                        Text(
                            "Ім'я", style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.ibarra_real_nova_variable_font_wght)),
                                color = colorResource(id = R.color.color_text2dark)
                            )
                        )
                    },
                    placeholder = {
                        Text(
                            "Введіть ім'я",
                            color = Color.Gray,
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.ibarra_real_nova_variable_font_wght))
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp), // Внутрішні відступи
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.color_Text), // Колір тексту
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.ibarra_real_nova_variable_font_wght))
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = colorResource(id = R.color.color_Text),  // Колір тексту при фокусі
                        unfocusedTextColor = colorResource(id = R.color.color_Text),  // Колір тексту без фокусу
                        focusedContainerColor = Color.Transparent,  // Фон при фокусі
                        unfocusedContainerColor = Color.Transparent,  // Фон без фокусу
                        cursorColor = colorResource(id = R.color.color_Text),  // Колір курсору
                        focusedIndicatorColor = colorResource(id = R.color.color_Text),  // Колір рамки при фокусі
                        unfocusedIndicatorColor = colorResource(id = R.color.color_Text)  // Колір рамки без фокусу
                    ),
                    shape = RoundedCornerShape(12.dp) // Заокруглені кути
                )

                // Радіо-кнопки для вибору статі
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    GenderRadioButton(
                        label = "Male",
                        selectedGender = selectedGender,
                        onSelect = { selectedGender = "Male" }
                    )
                    GenderRadioButton(
                        label = "Female",
                        selectedGender = selectedGender,
                        onSelect = { selectedGender = "Female" }
                    )
                }

                // Вибір іконки на основі вибраної статі
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    val icons = if (selectedGender == "Female") dataIconsFemale else dataIconsMale
                    items(icons) { iconId ->
                        IconSelectable(
                            iconId = iconId,
                            isSelected = selectedIcon == iconId,
                            onClick = { selectedIcon = iconId }
                        )
                    }
                }

                // Кнопки "Підтвердити" та "Відхилити"
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(
                        onClick = { onDismiss() },
                        modifier = Modifier.size(120.dp,70.dp)
                        ) {
                        Image(
                            painter = painterResource(id = R.drawable.cancel_confirm),
                            contentDescription = "Cancel",
                            modifier = Modifier.padding(5.dp)
                        )
                        Text(
                            text = "Cancel",
                            color = colorResource(id = R.color.color_Text),
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.ibarra_real_nova_variable_font_wght))
                            )
                        )

                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(
                        onClick = {
                            if (name.isNotBlank() && selectedIcon != null) {
                                onConfirm(name, selectedGender, selectedIcon!!)
                            }
                        },
                        modifier = Modifier.size(120.dp, 70.dp)
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
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.ibarra_real_nova_variable_font_wght))
                            )
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun GenderRadioButton(
    label: String,
    selectedGender: String,
    onSelect: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RadioButton(
            selected = selectedGender == label,
            onClick = onSelect,
            colors = RadioButtonDefaults.colors(
                unselectedColor = colorResource(id = R.color.color_text2),
                selectedColor = colorResource(id = R.color.color_text2),
            )
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = label,
            color = colorResource(id = R.color.color_Text),
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.ibarra_real_nova_variable_font_wght))
            )
            )
    }
}

@Composable
fun IconSelectable(
    iconId: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val iconSize by animateDpAsState(targetValue = if (isSelected) 90.dp else 78.dp) // Збільшуємо, якщо вибрано

    Box(
        modifier = Modifier
            .padding()
            .size(90.dp) // Використовуємо анімований розмір
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = "Icon",
            modifier = Modifier.size(iconSize - 8.dp) // Зображення трохи менше за Box
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingPlayerDialogPreview() {
    SettingPlayerDialog(
        onDismiss = { false },
        onConfirm = { name, gender, icon ->
            // Тут ви можете обробити підтвердження (наприклад, зберегти дані або показати повідомлення)
            println("Name: $name, Gender: $gender, Icon: $icon")
        },
        dataIcons = listOf(
            R.drawable.beautygirl1_1,
            R.drawable.beautygirl1_2,
            R.drawable.beautygirl1_3
        )
    )
}