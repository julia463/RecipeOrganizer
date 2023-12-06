package com.bignerdranch.android.recipeorganizer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.recipeorganizer.databinding.FragmentCreateRecipeBinding
import com.bignerdranch.android.recipeorganizer.databinding.FragmentRecipeListBinding

private var _binding:FragmentRecipeListBinding? = null
private val binding
    get() = checkNotNull(_binding){
        "Cannot access binding because it is null. Is the view visible?"
    }
private const val TAG = "CrimeListFragment"
class RecipeListFragment : Fragment() {
    private val recipeListViewModel: RecipeListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeListBinding.inflate(inflater,container,false)

         binding.RecipeRecyclerView.layoutManager = LinearLayoutManager(context)

        val recipes = recipeListViewModel.recipes
        val adapter = RecipeListAdapter(recipes)
        binding.RecipeRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }
}