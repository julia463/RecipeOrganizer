package com.bignerdranch.android.recipeorganizer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bignerdranch.android.recipeorganizer.Recipe

class RecipeDetailFragment : Fragment() {

    private lateinit var selectedRecipe: Recipe

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Check if selectedRecipe is set and if the view is not null
        if (::selectedRecipe.isInitialized && view != null) {
            // Populate the TextView fields with recipe details
            view.findViewById<TextView>(R.id.recipe_title)?.text = selectedRecipe.name
            view.findViewById<Spinner>(R.id.spinner)?.setSelection(getMealTypeIndex(selectedRecipe.mealType))
            view.findViewById<TextView>(R.id.preptime_info)?.text = selectedRecipe.prepTime
            view.findViewById<TextView>(R.id.servingsize_info)?.text = selectedRecipe.servingSize

            // Populate ingredients
            for (ingredient in selectedRecipe.ingredients.orEmpty()) {
                addIngredientTextView(ingredient)
            }

            // Populate steps
            for (step in selectedRecipe.steps.orEmpty()) {
                addStepTextView(step)
            }
        }
    }

    // Utility method to get the index of the meal type in the spinner
    private fun getMealTypeIndex(mealType: String?): Int {
        val mealTypesArray = resources.getStringArray(R.array.mealtype_array)
        return mealTypesArray.indexOf(mealType.orEmpty())
    }

    // Utility method to dynamically add TextViews for ingredients
    private fun addIngredientTextView(ingredient: String) {
        val ingredientTextView = createTextView(ingredient)
        view?.findViewById<ViewGroup>(R.id.ingredientsContainer)?.addView(ingredientTextView)
    }

    // Utility method to dynamically add TextViews for steps
    private fun addStepTextView(step: String) {
        val stepTextView = createTextView(step)
        view?.findViewById<ViewGroup>(R.id.stepsContainer)?.addView(stepTextView)
    }

    // Utility method to create a TextView with common styling
    private fun createTextView(text: String): TextView {
        val textView = TextView(requireContext())
        textView.text = text
        textView.textSize = resources.getDimension(R.dimen.text_size_medium)
        return textView
    }
    fun setRecipeDetails(recipe: Recipe) {
        selectedRecipe = recipe
    }
}

