package com.bignerdranch.android.recipeorganizer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


//class RecipeListFragment : Fragment() {
class RecipeListFragment : Fragment() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter
    //private val recipes: MutableList<Recipe> = mutableListOf()
    private var recipes: MutableList<Recipe> = mutableListOf()
    private var desiredMealType = "all"
    //Will be the one that is changed
    private var filteredRecipes: MutableList<Recipe> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_list, container, false)

        recyclerView = view.findViewById(R.id.RecipeRecyclerView)
        val mainView = R.layout.activity_main



        recyclerView.layoutManager = LinearLayoutManager(requireContext())



        // Initialize adapter with the current list of recipes
        recipeAdapter = RecipeAdapter(recipes, object : RecipeAdapter.OnItemClickListener {
        //recipeAdapter = RecipeAdapter(filteredRecipes, object : RecipeAdapter.OnItemClickListener {
            override fun onItemClick(recipe: Recipe) {
                // Handle item click, for example, show detailed view
                showRecipeDetails(recipe)
            }
        })

        recyclerView.adapter = recipeAdapter

        return view
    }

    override fun onViewCreated(view:View, savedInstanceState: Bundle?){
        super.onViewCreated(view,savedInstanceState)
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

    fun onRecipesFiltered(){

       try{
          // recipeAdapter.notifyItemRangeChanged(0,recipes.size-1); //recipeAdapter.notifyDataSetChanged()
           recipeAdapter = RecipeAdapter(recipes, object : RecipeAdapter.OnItemClickListener {
               //recipeAdapter = RecipeAdapter(filteredRecipes, object : RecipeAdapter.OnItemClickListener {
               override fun onItemClick(recipe: Recipe) {
                   // Handle item click, for example, show detailed view
                   showRecipeDetails(recipe)
               }
           })
           recyclerView.swapAdapter(recipeAdapter,false)
           Log.d("Current list of recipes","$recipes")
           Log.d("successful","slay")

       } catch (e:Exception) {
           Log.d("Exception","$e")

       }


    }



    fun filterByMealType(desiredMealType:String){
        Log.d("Desired meal type","$desiredMealType")

        //desiredMealType = (requireActivity() as MainActivity).getSpinnerSelectedItem()
        if(desiredMealType == "all"){
            recipes = recipes
        } else {
            //var filteredRecipes = recipes.filter{it.mealType == desiredMealType}
            recipes = recipes.filter{it.mealType == desiredMealType}.toMutableList()
        }


        // have to modify the original one
        //make a new list that will hold the original one

        val mainActivity = requireActivity() as? MainActivity
        mainActivity?.onRecipeListFiltered()

        //recipeAdapter.notifyDataSetChanged()
        //recipeAdapter.notifyItemRangeChanged(0,recipes.size)

        requireActivity().supportFragmentManager.popBackStack()

        Log.d("Recipes:","$recipes")
        Log.d("Result: ","$filteredRecipes")
    }
}

