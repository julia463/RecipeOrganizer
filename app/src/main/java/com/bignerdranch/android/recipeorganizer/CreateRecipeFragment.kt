package com.bignerdranch.android.recipeorganizer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.util.UUID

class CreateRecipeFragment : Fragment() {

    private lateinit var spinner: Spinner
    private lateinit var ingredientsContainer: LinearLayout
    private lateinit var stepsContainer: LinearLayout
    private var stepsCount = 1
    private var ingredientCount = 1

    val mealtype_array = arrayOf("select mealtype", "Breakfast", "Lunch", "Dinner", "Dessert")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_create_recipe, container, false)

        spinner = view.findViewById(R.id.spinner)

        val arrayAdapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            mealtype_array
        )
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem: String = mealtype_array[position]
                // Do something with the selected item
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Do nothing here
            }
        }

        // INGREDIENTS
        ingredientsContainer = view.findViewById(R.id.ingredientsContainer)

        val addIngredientButton: ImageButton = view.findViewById(R.id.add_ingredient_button)
        addIngredientButton.setOnClickListener {
            addNewIngredientEditText()
        }

        // STEPS
        stepsContainer = view.findViewById(R.id.stepsContainer)

        // Access the button and set its click listener
        val addStepButton: ImageButton = view.findViewById(R.id.add_step_button)
        addStepButton.setOnClickListener {
            addNewStepEditText()
        }

        // SUBMIT BUTTON
        val submitButton: Button = view.findViewById(R.id.submit_button)
        submitButton.setOnClickListener {
            onSubmitButtonClicked()
        }

        return view
    }

    private fun onSubmitButtonClicked() {
        // Get input values
        val nameEditText: EditText = view?.findViewById(R.id.recipe_title) ?: return
        val name = nameEditText.text.toString()
        val mealType = mealtype_array[spinner.selectedItemPosition]
        val prepTimeEditText: EditText = view?.findViewById(R.id.preptime_info) ?: return
        val prepTime = prepTimeEditText.text.toString()
        val servingSizeEditText: EditText = view?.findViewById(R.id.servingsize_info) ?: return
        val servingSize = servingSizeEditText.text.toString()
        val ingredients = getIngredients()
        val steps = getSteps()

        Log.d("meal type","$mealType")
       /* for(i in ingredients){
            Log.d("current ingredient","$i")
        }
        Log.d("ingredients","$ingredients")
        Log.d("steps","$steps")
        for(i in steps){
            Log.d("current step","$i")
        } */

        // Create a new Recipe object
        val newRecipe = Recipe(UUID.randomUUID(), name, mealType, prepTime, servingSize, ingredients, steps)

        // Notify the MainActivity about the new recipe
        val mainActivity = requireActivity() as? MainActivity
        mainActivity?.onRecipeCreated(newRecipe)

        // Pop the current fragment from the back stack
        requireActivity().supportFragmentManager.popBackStack()

        // Find the RecipeListFragment and notify it about the new recipe
        val recipeListFragment = requireActivity().supportFragmentManager.findFragmentByTag(RecipeListFragment::class.java.simpleName) as? RecipeListFragment
        recipeListFragment?.onRecipeCreated(newRecipe)

        Log.d("RecipeCreation", "New Recipe: $newRecipe")

    }

    private fun addNewIngredientEditText() {
        val newIngredientLayout = LinearLayout(requireContext())
        newIngredientLayout.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        newIngredientLayout.orientation = LinearLayout.HORIZONTAL

        val numberTextView = TextView(requireContext())
        val numberLayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        numberLayoutParams.marginEnd = resources.getDimensionPixelSize(R.dimen.spacing_small)
        numberTextView.layoutParams = numberLayoutParams
        numberTextView.text = "${ingredientCount++}."

        // Create a new EditText
        val newEditText = EditText(requireContext())
        val editTextLayoutParams = LinearLayout.LayoutParams(
            0,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            1f
        )
        editTextLayoutParams.marginStart = resources.getDimensionPixelSize(R.dimen.spacing_small)
        newEditText.layoutParams = editTextLayoutParams
        newEditText.hint = getString(R.string.ingredient_hint)

        newIngredientLayout.addView(numberTextView)
        newIngredientLayout.addView(newEditText)

        ingredientsContainer.addView(newIngredientLayout)
    }

    private fun addNewStepEditText() {
        val newStepLayout = LinearLayout(requireContext())
        newStepLayout.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        newStepLayout.orientation = LinearLayout.HORIZONTAL

        val numberTextView = TextView(requireContext())
        val numberLayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        numberLayoutParams.marginEnd = resources.getDimensionPixelSize(R.dimen.spacing_small)
        numberTextView.layoutParams = numberLayoutParams
        numberTextView.text = "${stepsCount++}."

        // Create a new EditText
        val newEditText = EditText(requireContext())
        val editTextLayoutParams = LinearLayout.LayoutParams(
            0,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            1f
        )
        editTextLayoutParams.marginStart = resources.getDimensionPixelSize(R.dimen.spacing_small)
        newEditText.layoutParams = editTextLayoutParams
        newEditText.hint = getString(R.string.step_hint)

        newStepLayout.addView(numberTextView)
        newStepLayout.addView(newEditText)

        stepsContainer.addView(newStepLayout)
    }

    private fun getIngredients(): List<String> {
        val ingredientsList: MutableList<String> = mutableListOf()

        for (i in 1..ingredientCount) {
            val editText: EditText? = ingredientsContainer.findViewWithTag("ingredient_$i")
            val ingredientText = editText?.text?.toString()?.trim()
            if (!ingredientText.isNullOrEmpty()) {
                ingredientsList.add(ingredientText)
            }
        }

        return ingredientsList
    }

    private fun getSteps(): List<String> {
        val stepsList: MutableList<String> = mutableListOf()

        for (i in 1..stepsCount) {
            val editText: EditText? = stepsContainer.findViewWithTag("step_$i")
            val stepText = editText?.text?.toString()?.trim()
            if (!stepText.isNullOrEmpty()) {
                stepsList.add(stepText)
            }
        }

        return stepsList
    }
}
