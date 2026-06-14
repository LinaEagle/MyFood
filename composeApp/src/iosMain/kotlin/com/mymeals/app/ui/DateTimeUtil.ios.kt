package com.mymeals.app.ui

import platform.Foundation.NSCalendar
import platform.Foundation.NSDate
import platform.Foundation.NSDateComponents
import platform.Foundation.NSCalendarUnitDay
import platform.Foundation.NSCalendarUnitHour
import platform.Foundation.NSCalendarUnitMinute
import platform.Foundation.NSCalendarUnitMonth
import platform.Foundation.NSCalendarUnitYear

actual fun setTimeOfDay(dateMillis: Long, hour: Int, minute: Int): Long {
    val calendar = NSCalendar.currentNSCalendar
    val date = NSDate(timeIntervalSince1970 = dateMillis / 1000.0)
    val components = calendar.components(
        NSCalendarUnitYear or NSCalendarUnitMonth or NSCalendarUnitDay,
        date
    )
    components.hour = hour.toLong()
    components.minute = minute.toLong()
    components.second = 0L
    val result = calendar.dateFromComponents(components) ?: return dateMillis
    return (result.timeIntervalSince1970 * 1000).toLong()
}

actual fun getHour(timestamp: Long): Int {
    val calendar = NSCalendar.currentNSCalendar
    val components = calendar.components(
        NSCalendarUnitHour,
        NSDate(timeIntervalSince1970 = timestamp / 1000.0)
    )
    return components.hour?.toInt() ?: 0
}

actual fun getMinute(timestamp: Long): Int {
    val calendar = NSCalendar.currentNSCalendar
    val components = calendar.components(
        NSCalendarUnitMinute,
        NSDate(timeIntervalSince1970 = timestamp / 1000.0)
    )
    return components.minute?.toInt() ?: 0
}
