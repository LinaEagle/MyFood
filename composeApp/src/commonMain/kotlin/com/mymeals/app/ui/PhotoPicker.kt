package com.mymeals.app.ui

import androidx.compose.runtime.Composable

@Composable
expect fun rememberPhotoPickerLauncher(onPhotoPicked: (ByteArray) -> Unit): () -> Unit
