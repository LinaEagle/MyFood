package com.mymeals.app.ui

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

actual fun formatTimestamp(timestamp: Long): String {
    val sdf = SimpleDateFormat("d MMM yyyy, HH:mm", Locale.getDefault())
    return sdf.format(Date(timestamp))
}

actual fun formatDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("d MMMM yyyy", Locale("ru"))
    return sdf.format(Date(timestamp))
}
