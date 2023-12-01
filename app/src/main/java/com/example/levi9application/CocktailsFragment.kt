package com.example.levi9application

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.levi9application.databinding.FragmentCocktailsBinding

class CocktailsFragment : Fragment(R.layout.fragment_cocktails) {
    private lateinit var binding: FragmentCocktailsBinding
    private lateinit var adapter: CocktailAdapter
    private lateinit var list: List<Cocktail>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentCocktailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvSetup()
    }

    private fun dataInit(): List<Cocktail> {
        return listOf(
            Cocktail(
                R.drawable.image, context?.getString(R.string.placeholder) ?: "Not found", false
            ), Cocktail(
                R.drawable.image, context?.getString(R.string.placeholder) ?: "Not found", false
            ), Cocktail(
                R.drawable.image, context?.getString(R.string.placeholder) ?: "Not found", false
            ), Cocktail(
                R.drawable.image, context?.getString(R.string.placeholder) ?: "Not found", false
            )
        )
    }

    private fun rvSetup(){
        list = dataInit()

        adapter = CocktailAdapter(list)
        binding.rViewCocktails.adapter = adapter

        val layoutManager = GridLayoutManager(context, 2)
        binding.rViewCocktails.layoutManager = layoutManager
    }
}