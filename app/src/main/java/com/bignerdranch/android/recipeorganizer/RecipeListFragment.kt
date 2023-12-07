package com.bignerdranch.android.recipeorganizer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecipeListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter
    private val recipes: MutableList<Recipe> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_list, container, false)

        recyclerView = view.findViewById(R.id.RecipeRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize adapter with the current list of recipes
        recipeAdapter = RecipeAdapter(recipes, object : RecipeAdapter.OnItemClickListener {
            override fun onItemClick(recipe: Recipe) {
                // Handle item click, for example, show detailed view
                showRecipeDetails(recipe)
            }
        })

        recyclerView.adapter = recipeAdapter

        return view
    }

    // Replace this with your actual method to show recipe details
    private fun showRecipeDetails(recipe: Recipe) {
        val recipeDetailFragment = RecipeDetailFragment()
        recipeDetailFragment.setRecipeDetails(recipe)

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, recipeDetailFragment)
            .addToBackStack(null)
            .commit()
    }

    // Call this method when the user creates a new recipe
    fun onRecipeCreated(newRecipe: Recipe) {
        // Add the new recipe to the list and update the adapter
        recipes.add(newRecipe)
        recipeAdapter.notifyItemInserted(recipes.size - 1)

        Log.d("RecipeListFragment", "Recipe added: $newRecipe")

    }
}

