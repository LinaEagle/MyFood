package com.mymeals.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.mymeals.app.data.MealRepository
import com.mymeals.app.data.currentTimeMillis
import com.mymeals.app.data.getAppStorageDir
import com.mymeals.app.model.Meal
import kotlin.random.Random

private enum class Screen { MAIN, ADD_MEAL }

@Composable
fun App(openCamera: Boolean = false) {
    val storageDir = remember { getAppStorageDir() }
    val repository = remember { MealRepository(storageDir) }

    var meals by remember { mutableStateOf(repository.loadMeals()) }
    var consumeOpenCamera by remember { mutableStateOf(openCamera) }
    var screen by remember { mutableStateOf(if (consumeOpenCamera) Screen.ADD_MEAL else Screen.MAIN) }

    LaunchedEffect(consumeOpenCamera) {
        if (consumeOpenCamera) consumeOpenCamera = false
    }

    MaterialTheme {
        when (screen) {
            Screen.MAIN -> MainScreen(
                meals = meals,
                onAddClick = { screen = Screen.ADD_MEAL },
                onDeleteMeals = { ids ->
                    val deleted = meals.filter { it.id in ids }
                    deleted.forEach { repository.deletePhoto(it.photoPath) }
                    meals = meals.filter { it.id !in ids }
                    repository.saveMeals(meals)
                },
            )

            Screen.ADD_MEAL -> AddMealScreen(
                autoOpenCamera = consumeOpenCamera,
                onSave = { photoBytes, name, calories, weightGrams, createdAt ->
                    val id = "${currentTimeMillis()}_${Random.nextInt(10000)}"
                    val photoPath = repository.savePhoto(id, photoBytes)
                    val meal = Meal(
                        id = id,
                        photoPath = photoPath,
                        name = name,
                        calories = calories,
                        weightGrams = weightGrams,
                        createdAt = createdAt,
                    )
                    meals = (listOf(meal) + meals).sortedByDescending { it.createdAt }
                    repository.saveMeals(meals)
                    screen = Screen.MAIN
                },
                onCancel = { screen = Screen.MAIN },
            )
        }
    }
}
