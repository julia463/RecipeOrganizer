package com.bignerdranch.android.recipeorganizer

import androidx.lifecycle.ViewModel
import java.util.UUID

class RecipeListViewModel: ViewModel() {

    val recipes = mutableListOf<Recipe>()

    init{
        for(i in 0 until 100){
            val recipe = Recipe(
                id= UUID.randomUUID(),
                name = "Recipe #$i",
                mealType="Breakfast",
                prepTime = 50,
                servingSize=4,
                ingredients=listOf("Eggs","Milk","Butter"),
                steps=listOf("Preheat stove","Stir ingredients","cook"),
                similarRecipes=listOf("waffles","pancakes","french toast"),
                dietaryAccomodations=listOf("vegetarian"),
                photoFileName=""

            )
            recipes += recipe
        }
    }
}
