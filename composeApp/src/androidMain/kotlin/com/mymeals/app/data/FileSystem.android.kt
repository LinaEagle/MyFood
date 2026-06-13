package com.mymeals.app.data

import java.io.File

actual object FileSystem {
    actual fun readBytes(path: String): ByteArray? = try {
        File(path).readBytes()
    } catch (_: Exception) {
        null
    }

    actual fun writeBytes(path: String, bytes: ByteArray) {
        File(path).writeBytes(bytes)
    }

    actual fun delete(path: String) {
        File(path).delete()
    }

    actual fun exists(path: String): Boolean = File(path).exists()

    actual fun createDirectories(path: String) {
        File(path).mkdirs()
    }
}
