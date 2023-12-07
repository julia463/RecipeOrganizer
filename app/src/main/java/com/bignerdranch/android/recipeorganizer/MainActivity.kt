package com.bignerdranch.android.recipeorganizer

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext

class MainActivity : AppCompatActivity() {


    private lateinit var spinner: Spinner


    val filterbyarray = arrayOf("all","breakfast","lunch","dinner","dessert")
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

        //Handle the click event of the filter button, and set the spinner

        spinner = findViewById<Spinner>(R.id.filter)

        val arrayAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            filterbyarray
        )

        spinner.adapter = arrayAdapter
    }



    fun onAppNameClick(view: View) {
        val recipeListFragment = RecipeListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, recipeListFragment)
            .commit()
    }
}
