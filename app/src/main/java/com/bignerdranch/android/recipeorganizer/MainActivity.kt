package com.bignerdranch.android.recipeorganizer

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val recipeListFragment = RecipeListFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, recipeListFragment)
                .commit()
        }

        // Handle the click event for the create_recipe_button
        findViewById<ImageView>(R.id.create_recipe_button).setOnClickListener {
            val createRecipeFragment = CreateRecipeFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, createRecipeFragment)
                .addToBackStack(null)
                .commit()
        }

        findViewById<TextView>(R.id.home_button).setOnClickListener {
            val recipeListFragment = RecipeListFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, recipeListFragment)
                .commit()
        }
    }

    fun onAppNameClick(view: View) {
        val recipeListFragment = RecipeListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, recipeListFragment)
            .commit()
    }
}
