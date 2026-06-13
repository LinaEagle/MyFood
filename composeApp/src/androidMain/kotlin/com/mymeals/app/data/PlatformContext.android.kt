package com.mymeals.app.data

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
lateinit var appContext: Context

actual fun getAppStorageDir(): String = appContext.filesDir.absolutePath
actual fun currentTimeMillis(): Long = System.currentTimeMillis()
