package com.mymeals.app.ui

import androidx.compose.ui.graphics.ImageBitmap

expect fun loadImageBitmap(path: String): ImageBitmap?
expect fun decodeImageBitmap(bytes: ByteArray): ImageBitmap?
