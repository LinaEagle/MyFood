package com.mymeals.app.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import java.io.File

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
                bytes?.let(onPhotoPicked)
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
