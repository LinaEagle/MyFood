package com.mymeals.app.ui

import androidx.compose.ui.graphics.ImageBitmap

object ImageCache {
    private const val MAX_SIZE = 50
    private val map = mutableMapOf<String, ImageBitmap>()
    private val order = mutableListOf<String>()

    @Synchronized
    fun get(key: String): ImageBitmap? {
        val value = map[key]
        if (value != null) {
            order.remove(key)
            order.add(key)
        }
        return value
    }

    @Synchronized
    fun put(key: String, value: ImageBitmap) {
        if (map.containsKey(key)) {
            order.remove(key)
        }
        while (order.size >= MAX_SIZE) {
            val oldest = order.removeAt(0)
            map.remove(oldest)
        }
        map[key] = value
        order.add(key)
    }

    @Synchronized
    fun remove(key: String) {
        map.remove(key)
        order.remove(key)
    }
}
