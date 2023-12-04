package com.example.levi9application

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.levi9application.databinding.FragmentCocktailsBinding
import com.example.levi9application.model.Cocktail
import com.example.levi9application.viewModel.CocktailViewModel

class CocktailsFragment : Fragment(R.layout.fragment_cocktails) {
    private var _binding: FragmentCocktailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CocktailAdapter
    private lateinit var list: MutableList<Cocktail>
    var cocktailViewModel: CocktailViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailsBinding.inflate(inflater, container, false)

        cocktailViewModel = ViewModelProvider(this)[CocktailViewModel::class.java]

        cocktailViewModel!!.getCocktailList.observe(viewLifecycleOwner) { cocktailModels ->
            if (cocktailModels != null) {
                Log.e("Cocktail", "CocktailList" + cocktailModels.cocktails)

                list = cocktailModels.cocktails

                rvSetup()

            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun rvSetup() {

        adapter = CocktailAdapter(list)

        binding.rViewCocktails!!.adapter = adapter

        val layoutManager = GridLayoutManager(context, 2)
        binding.rViewCocktails.layoutManager = layoutManager
    }
}