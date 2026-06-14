package com.mymeals.app.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import java.io.ByteArrayOutputStream
import java.io.File

private const val MAX_DIMENSION = 1024
private const val JPEG_QUALITY = 80

private fun compressImage(bytes: ByteArray): ByteArray {
    val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size) ?: return bytes

    val (w, h) = scaleFit(bitmap.width, bitmap.height, MAX_DIMENSION)
    if (w == bitmap.width && h == bitmap.height) {
        val result = compressToJpeg(bitmap)
        bitmap.recycle()
        return result
    }

    val scaled = Bitmap.createScaledBitmap(bitmap, w, h, true)
    bitmap.recycle()
    val result = compressToJpeg(scaled)
    scaled.recycle()
    return result
}

private fun scaleFit(w: Int, h: Int, max: Int): Pair<Int, Int> {
    if (w <= max && h <= max) return Pair(w, h)
    return if (w >= h) Pair(max, h * max / w) else Pair(w * max / h, max)
}

private fun compressToJpeg(bitmap: Bitmap): ByteArray {
    val out = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, JPEG_QUALITY, out)
    return out.toByteArray()
}

@Composable
actual fun rememberPhotoPickerLauncher(onPhotoPicked: (ByteArray) -> Unit): () -> Unit {
    val context = LocalContext.current

    val photoFile = remember {
        val dir = File(context.cacheDir, "camera")
        dir.mkdirs()
        File(dir, "capture.jpg")
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri = result.data?.data ?: if (photoFile.exists()) {
                FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", photoFile)
            } else null

            uri?.let {
                val bytes = context.contentResolver.openInputStream(it)?.use { stream ->
                    stream.readBytes()
                }
                if (bytes != null) {
                    onPhotoPicked(compressImage(bytes))
                }
            }
        }
    }

    return remember {
        {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", photoFile)
                putExtra(MediaStore.EXTRA_OUTPUT, uri)
                addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            }

            val galleryIntent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "image/*"
            }

            val chooser = Intent.createChooser(galleryIntent, "Добавить фото").apply {
                putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))
            }

            launcher.launch(chooser)
        }
    }
}
