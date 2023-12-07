package com.bignerdranch.android.recipeorganizer

import java.util.UUID

data class Recipe(
    val id: UUID,
    val name: String?,
    val mealType: String?,
    val prepTime: String?,
    val servingSize: String?,
    val ingredients: List<String>?,
    val steps: List<String>?,
)

