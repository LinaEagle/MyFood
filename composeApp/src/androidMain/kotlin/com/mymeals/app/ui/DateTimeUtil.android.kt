package com.mymeals.app.ui

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

actual fun setTimeOfDay(dateMillis: Long, hour: Int, minute: Int): Long {
    val cal = Calendar.getInstance()
    cal.timeInMillis = dateMillis
    cal.set(Calendar.HOUR_OF_DAY, hour)
    cal.set(Calendar.MINUTE, minute)
    cal.set(Calendar.SECOND, 0)
    cal.set(Calendar.MILLISECOND, 0)
    return cal.timeInMillis
}

actual fun getHour(timestamp: Long): Int {
    val cal = Calendar.getInstance()
    cal.timeInMillis = timestamp
    return cal.get(Calendar.HOUR_OF_DAY)
}

actual fun getMinute(timestamp: Long): Int {
    val cal = Calendar.getInstance()
    cal.timeInMillis = timestamp
    return cal.get(Calendar.MINUTE)
}

actual fun formatDateInput(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return sdf.format(Date(timestamp))
}

actual fun parseDateInput(dateStr: String): Long? {
    val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    sdf.isLenient = false
    return sdf.parse(dateStr)?.time
}
