package com.mymeals.app.data

import com.mymeals.app.model.Meal
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MealRepository(private val storageDir: String) {

    private val photosDir get() = "$storageDir/photos"
    private val jsonFile get() = "$storageDir/meals.json"

    fun loadMeals(): List<Meal> {
        val bytes = FileSystem.readBytes(jsonFile) ?: return emptyList()
        return try {
            Json.decodeFromString<List<Meal>>(bytes.decodeToString())
        } catch (_: Exception) {
            emptyList()
        }
    }

    fun saveMeals(meals: List<Meal>) {
        val json = Json.encodeToString(meals)
        FileSystem.createDirectories(storageDir)
        FileSystem.writeBytes(jsonFile, json.encodeToByteArray())
    }

    fun savePhoto(id: String, bytes: ByteArray): String {
        FileSystem.createDirectories(photosDir)
        val path = "$photosDir/$id.jpg"
        FileSystem.writeBytes(path, bytes)
        return path
    }

    fun deletePhoto(photoPath: String) {
        FileSystem.delete(photoPath)
    }
}
