package com.mymeals.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.window.Dialog
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
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
import com.mymeals.app.data.currentTimeMillis
import com.mymeals.app.ui.BackHandler
import com.mymeals.app.ui.decodeImageBitmap
import com.mymeals.app.ui.formatDate
import com.mymeals.app.ui.formatTimestamp
import com.mymeals.app.ui.getHour
import com.mymeals.app.ui.getMinute
import com.mymeals.app.ui.rememberPhotoPickerLauncher
import com.mymeals.app.ui.setTimeOfDay
import com.mymeals.app.ui.icons.photo_camera

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMealScreen(
    onSave: (photoBytes: ByteArray, name: String?, calories: Int?, createdAt: Long) -> Unit,
    onCancel: () -> Unit,
) {
    var photoBytes by remember { mutableStateOf<ByteArray?>(null) }
    var name by remember { mutableStateOf("") }
    var caloriesText by remember { mutableStateOf("") }

    val now = remember { currentTimeMillis() }
    var selectedDateMillis by remember { mutableStateOf(now) }
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    val launchPicker = rememberPhotoPickerLauncher { bytes ->
        photoBytes = bytes
    }

    val canSave = photoBytes != null

    BackHandler(onBack = onCancel)

    if (showDatePicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = selectedDateMillis
        )
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { dateMillis ->
                        selectedDateMillis = setTimeOfDay(
                            dateMillis,
                            getHour(selectedDateMillis),
                            getMinute(selectedDateMillis)
                        )
                    }
                    showDatePicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Отмена")
                }
            },
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (showTimePicker) {
        val timePickerState = rememberTimePickerState(
            initialHour = getHour(selectedDateMillis),
            initialMinute = getMinute(selectedDateMillis),
            is24Hour = true,
        )
        Dialog(
            onDismissRequest = { showTimePicker = false },
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                tonalElevation = 6.dp,
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    TimeInput(state = timePickerState)
                    Spacer(Modifier.height(24.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        TextButton(onClick = { showTimePicker = false }) {
                            Text("Отмена")
                        }
                        TextButton(onClick = {
                            selectedDateMillis = setTimeOfDay(
                                selectedDateMillis,
                                timePickerState.hour,
                                timePickerState.minute
                            )
                            showTimePicker = false
                        }) {
                            Text("OK")
                        }
                    }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Новая запись") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Button(
                    onClick = launchPicker,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                ) {
                    Icon(
                        photo_camera,
                        contentDescription = "Добавить фото",
                        modifier = Modifier.size(28.dp),
                    )
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

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showDatePicker = true }
                ) {
                    OutlinedTextField(
                        value = formatDate(selectedDateMillis),
                        onValueChange = {},
                        label = { Text("Дата") },
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = true,
                        singleLine = true,
                    )
                }

                val timeText = remember(selectedDateMillis) {
                    formatTimestamp(selectedDateMillis).substringAfter(", ")
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showTimePicker = true }
                ) {
                    OutlinedTextField(
                        value = timeText,
                        onValueChange = {},
                        label = { Text("Время") },
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = true,
                        singleLine = true,
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

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
                        onSave(photo, name.ifBlank { null }, calValue, selectedDateMillis)
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
