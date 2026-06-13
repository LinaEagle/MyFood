package com.mymeals.app.data

expect object FileSystem {
    fun readBytes(path: String): ByteArray?
    fun writeBytes(path: String, bytes: ByteArray)
    fun delete(path: String)
    fun exists(path: String): Boolean
    fun createDirectories(path: String)
}
