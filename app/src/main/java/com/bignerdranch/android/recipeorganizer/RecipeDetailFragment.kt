package com.bignerdranch.android.recipeorganizer


import android.app.Fragment
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.widget.doOnTextChanged
import com.bignerdranch.android.recipeorganizer.databinding.FragmentCreateRecipeBinding
import java.util.UUID

class RecipeDetailFragment: Fragment() {

    private  var _binding: FragmentCreateRecipeBinding? = null
    private val binding
        get() = checkNotNull(_binding){
            "Cannot access binding because it is null. Is the view visible?"
        }
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateRecipeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState:Bundle?){
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            //extract the recipe title
            recipeTitle.doOnTextChanged { text, _, _, _ ->
                recipe = recipe.copy(name = text.toString());
            }

            //extract the meal type (mealType)
            preptimeInfo.doOnTextChanged{ text, _, _, _ ->
                var prepString = text.toString()

                var prepTimeNum = prepString.toInt()
                recipe = recipe.copy(prepTime = prepTimeNum)
            }

            servingSize.doOnTextChanged{text, _, _, _ ->

                var servingString = text.toString()

                var servingSizeNum = servingString.toInt()

                recipe = recipe.copy(servingSize = servingSizeNum)
            }

            //figure out how to collect each ingredient

            //figure out how to collect each step.

             fun onDestroyView() {
                super.onDestroyView()
                _binding = null
            }





        }

    }

    override fun onDestroyView(){
        super.onDestroyView()
       // binding = null
    }

}