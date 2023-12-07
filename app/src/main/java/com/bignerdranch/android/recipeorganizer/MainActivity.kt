package com.bignerdranch.android.recipeorganizer

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val recipeListFragment = RecipeListFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, recipeListFragment, RecipeListFragment::class.java.simpleName)
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
            replaceFragment(recipeListFragment, true)
        }
    }

    fun onRecipeCreated(newRecipe: Recipe) {
        // Add the new recipe to the list and update the adapter
        val recipeListFragment = supportFragmentManager.findFragmentById(R.id.container) as? RecipeListFragment
        recipeListFragment?.onRecipeCreated(newRecipe)

        Log.d("RecipeList", "Recipe added: $newRecipe")
    }

    fun onAppNameClick(view: View) {
        val recipeListFragment = RecipeListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, recipeListFragment)
            .commit()
    }

    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }

}
