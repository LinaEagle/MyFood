package com.mymeals.app.ui

import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale

actual fun formatTimestamp(timestamp: Long): String {
    val formatter = NSDateFormatter()
    formatter.dateFormat = "d MMM yyyy, HH:mm"
    val date = NSDate(timeIntervalSince1970 = timestamp / 1000.0)
    return formatter.stringFromDate(date)
}

actual fun formatDate(timestamp: Long): String {
    val formatter = NSDateFormatter()
    formatter.dateFormat = "d MMMM yyyy"
    formatter.locale = NSLocale("ru_RU")
    val date = NSDate(timeIntervalSince1970 = timestamp / 1000.0)
    return formatter.stringFromDate(date)
}
