package com.mymeals.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mymeals.app.ui.decodeImageBitmap
import com.mymeals.app.ui.rememberPhotoPickerLauncher

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMealScreen(
    onSave: (photoBytes: ByteArray, name: String?, calories: Int?) -> Unit,
    onCancel: () -> Unit,
) {
    var photoBytes by remember { mutableStateOf<ByteArray?>(null) }
    var name by remember { mutableStateOf("") }
    var caloriesText by remember { mutableStateOf("") }

    val launchPicker = rememberPhotoPickerLauncher { bytes ->
        photoBytes = bytes
    }

    val canSave = photoBytes != null

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Новая запись") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Button(
                onClick = launchPicker,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Выбрать фото из галереи")
            }

            if (photoBytes != null) {
                val bitmap = remember(photoBytes) {
                    photoBytes?.let { decodeImageBitmap(it) }
                }
                if (bitmap != null) {
                    Image(
                        bitmap = bitmap,
                        contentDescription = "Предпросмотр",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop,
                    )
                }
            }

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Название (необязательно)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            OutlinedTextField(
                value = caloriesText,
                onValueChange = { caloriesText = it.filter { c -> c.isDigit() } },
                label = { Text("Калории (необязательно)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )

            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                OutlinedButton(
                    onClick = onCancel,
                    modifier = Modifier.weight(1f),
                ) {
                    Text("Отмена")
                }

                Button(
                    onClick = {
                        val photo = photoBytes ?: return@Button
                        val calValue = caloriesText.toIntOrNull()
                        onSave(photo, name.ifBlank { null }, calValue)
                    },
                    modifier = Modifier.weight(1f),
                    enabled = canSave,
                ) {
                    Text("Сохранить")
                }
            }
        }
    }
}
