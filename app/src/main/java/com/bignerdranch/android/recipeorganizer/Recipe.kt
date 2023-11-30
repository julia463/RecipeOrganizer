package com.bignerdranch.android.recipeorganizer

import java.util.UUID

data class Recipe(
    val id: UUID,
    val name: String,
    val mealType: String,
    val prepTime: Int,
    val servingSize: Int,
    val ingredients: List<String>,
    val steps: List<String>,
    val similarRecipes: List<String>,
    val dietaryAccomodations: List<String>,
    val photoFileName: String? = null,
)
