package com.bignerdranch.android.recipeorganizer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner


class CreateRecipe : Fragment() {

    private lateinit var spinner: Spinner

    val mealtype_array = arrayOf("Breakfast", "Lunch", "Dinner", "Dessert")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_recipe, container, false)

        // Access the Spinner from the inflated layout
        spinner = view.findViewById(R.id.spinner)

        // Set up ArrayAdapter and other configurations
        val arrayAdapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            mealtype_array
        )
        spinner.adapter = arrayAdapter

        // Set the OnItemSelectedListener
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

        return view
    }
}
