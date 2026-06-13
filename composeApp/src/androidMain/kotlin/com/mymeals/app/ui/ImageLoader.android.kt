package com.mymeals.app.ui

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

actual fun loadImageBitmap(path: String): ImageBitmap? {
    return BitmapFactory.decodeFile(path)?.asImageBitmap()
}

actual fun decodeImageBitmap(bytes: ByteArray): ImageBitmap? {
    return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)?.asImageBitmap()
}
