package com.mymeals.app.ui

expect fun setTimeOfDay(dateMillis: Long, hour: Int, minute: Int): Long

expect fun getHour(timestamp: Long): Int

expect fun getMinute(timestamp: Long): Int
