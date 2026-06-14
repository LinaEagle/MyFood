package com.mymeals.app.ui

expect fun setTimeOfDay(dateMillis: Long, hour: Int, minute: Int): Long

expect fun getHour(timestamp: Long): Int

expect fun getMinute(timestamp: Long): Int

expect fun formatDateInput(timestamp: Long): String

expect fun parseDateInput(dateStr: String): Long?

fun formatTimeInput(timestamp: Long): String {
    val h = getHour(timestamp).toString().padStart(2, '0')
    val m = getMinute(timestamp).toString().padStart(2, '0')
    return "$h:$m"
}

fun parseTimeInput(timeStr: String): Pair<Int, Int>? {
    val parts = timeStr.split(":")
    if (parts.size != 2) return null
    val h = parts[0].toIntOrNull() ?: return null
    val m = parts[1].toIntOrNull() ?: return null
    if (h !in 0..23 || m !in 0..59) return null
    return Pair(h, m)
}
