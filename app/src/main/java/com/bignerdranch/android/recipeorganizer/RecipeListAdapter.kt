package com.bignerdranch.android.recipeorganizer

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.recipeorganizer.databinding.ListItemRecipeBinding

class RecipeHolder(
    private val binding: ListItemRecipeBinding
) : RecyclerView.ViewHolder(binding.root){
    fun bind(recipe:Recipe){
        binding.recipeName.text= recipe.name
        binding.mealType.text = recipe.mealType


        binding.root.setOnClickListener{
            Toast.makeText(
                binding.root.context,
                "${recipe.name} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class RecipeListAdapter(
    private val recipes: List<Recipe>
) : RecyclerView.Adapter<RecipeHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemRecipeBinding.inflate(inflater,parent,false)
        return RecipeHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int){
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount() = recipes.size
}