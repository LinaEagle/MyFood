package com.mymeals.app.model

import kotlinx.serialization.Serializable

@Serializable
data class Meal(
    val id: String,
    val photoPath: String,
    val name: String? = null,
    val calories: Int? = null,
    val weightGrams: Int? = null,
    val createdAt: Long,
)
