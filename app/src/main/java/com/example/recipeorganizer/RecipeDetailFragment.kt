package com.example.recipeorganizer

import android.app.Fragment
import android.os.Bundle
import java.util.UUID

class RecipeDetailFragment: Fragment() {

    private lateinit var recipe: Recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recipe = Recipe(
            id = UUID.randomUUID(),
            name="",
            mealType="breakfast",
            prepTime=0,
            servingSize=4,
            ingredients=listOf<String>(),
            steps=listOf<String>(),
            similarRecipes=listOf<String>(),
            dietaryAccomodations=listOf<String>()

        )
    }

}