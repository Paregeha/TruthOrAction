package com.example.truthoraction.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SettingPlayerDialog(onDismiss: () -> Unit, onConfirm: (String, String, String) -> Unit) {
    var name by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var selectedIcon by remember { mutableStateOf("") }

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
                OutlinedTextField(
                    value = gender,
                    onValueChange = { gender = it },
                    label = { Text("Стать") }
                )
                OutlinedTextField(
                    value = selectedIcon,
                    onValueChange = { selectedIcon = it },
                    label = { Text("Іконка") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                if (name.isNotBlank() && gender.isNotBlank() && selectedIcon.isNotBlank()) {
                    onConfirm(name, gender, selectedIcon)
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

@Preview(showBackground = true)
@Composable
fun SettingPlayerDialogPreview() {
    SettingPlayerDialog(
        onDismiss = { false },
        onConfirm = { name, gender, icon ->
            // Тут ви можете обробити підтвердження (наприклад, зберегти дані або показати повідомлення)
            println("Name: $name, Gender: $gender, Icon: $icon")
        }
    )
}