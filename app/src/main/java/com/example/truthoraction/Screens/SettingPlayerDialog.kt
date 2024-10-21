package com.example.truthoraction.Screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.truthoraction.R

@Composable
fun SettingPlayerDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String, Int) -> Unit, // Змінили тип selectedIcon на Int для ID ресурсу
    dataIcons: List<Int>
) {
    var name by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("Male") } // За замовчуванням обрано Male
    var selectedIcon by remember { mutableStateOf<Int?>(null) } // Зберігаємо ID вибраної іконки


    val dataIcons = remember {
        mutableStateListOf(
            R.drawable.beautygirl1_1,
            R.drawable.beautygirl1_2,
            R.drawable.beautygirl1_3
        )
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Введіть дані") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Ім'я") }
                )

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

                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(dataIcons) { iconId ->
                        IconSelectable(
                            iconId = iconId,
                            isSelected = selectedIcon == iconId,
                            onClick = { selectedIcon = iconId }
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                if (name.isNotBlank() && selectedIcon != null) {
                    onConfirm(name, selectedGender, selectedIcon!!)
                }
            }) {
                Text("Підтвердити")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Відхилити")
            }
        }
    )
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
            onClick = onSelect
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = label)
    }
}

@Composable
fun IconSelectable(
    iconId: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val iconSize by animateDpAsState(targetValue = if (isSelected) 72.dp else 64.dp) // Збільшуємо, якщо вибрано

    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(iconSize) // Використовуємо анімований розмір
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