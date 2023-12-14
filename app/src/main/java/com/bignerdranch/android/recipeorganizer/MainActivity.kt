package com.bignerdranch.android.recipeorganizer

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    lateinit var recipeListFragment: RecipeListFragment

    val filterbyarray = arrayOf("All","Breakfast","Lunch","Dinner","Dessert")
    var spinnerSelectedItem = "All";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            //val recipeListFragment = RecipeListFragment()
            recipeListFragment = RecipeListFragment()
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
           // val recipeListFragment = RecipeListFragment()
            recipeListFragment = RecipeListFragment()
            replaceFragment(recipeListFragment, true)
        }

        //Handle the click event for the filter button, and set the sponner
        spinner = findViewById<Spinner>(R.id.filter)

        val arrayAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            filterbyarray
        )
        spinner.adapter = arrayAdapter;

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem: String = filterbyarray[position]
                spinnerSelectedItem = selectedItem;
                val recipeListFragment = supportFragmentManager.findFragmentById(R.id.container) as? RecipeListFragment
                Log.d("Current recipe fragment","$recipeListFragment")
                recipeListFragment?.filterByMealType(spinnerSelectedItem)
               Log.d("Selected item: ", "$selectedItem")
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Do nothing here
            }
        }
    }
    /*fun getSpinnerSelectedItem(): String{
        return spinnerSelectedItem;
    } */
    fun onRecipeCreated(newRecipe: Recipe) {
        // Add the new recipe to the list and update the adapter
        val recipeListFragment = supportFragmentManager.findFragmentById(R.id.container) as? RecipeListFragment
        //recipeListFragment = (supportFragmentManager.findFragmentById(R.id.container) as? RecipeListFragment)!!
        recipeListFragment?.onRecipeCreated(newRecipe)

        Log.d("RecipeList", "Recipe added: $newRecipe")
    }

    fun onRecipeListFiltered(){
        //Update the adapter
        val recipeListFragment = supportFragmentManager.findFragmentById(R.id.container) as? RecipeListFragment
        recipeListFragment?.onRecipesFiltered()
    }
    fun onAppNameClick(view: View) {
        val recipeListFragment = RecipeListFragment()
       // recipeListFragment = RecipeListFragment()
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
