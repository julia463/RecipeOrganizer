package com.bignerdranch.android.recipeorganizer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID


private const val TAG = "CrimeListViewModel"

class RecipeListViewModel: ViewModel() {

    val recipes = mutableListOf<Recipe>()

    init{
        viewModelScope.launch {

                recipes += loadRecipes()
            }
        }
    }

suspend fun loadRecipes(): List<Recipe>{
    val result = mutableListOf<Recipe>()
    delay(5000)
    for (i in 0 until 100) {
        val recipe = Recipe(
            id = UUID.randomUUID(),
            name = "Recipe #$i",
            mealType = "Breakfast",
            prepTime = 50,
            servingSize = 4,
            ingredients = listOf("Eggs", "Milk", "Butter"),
            steps = listOf("Preheat stove", "Stir ingredients", "cook"),
            similarRecipes = listOf("waffles", "pancakes", "french toast"),
            dietaryAccomodations = listOf("vegetarian"),
            photoFileName = ""
        )
        result += recipe
    }
    return result
}
