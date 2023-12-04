package com.example.levi9application

import android.app.AlertDialog
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
import com.example.levi9application.model.Resource
import com.example.levi9application.viewModel.CocktailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CocktailsFragment : Fragment(R.layout.fragment_cocktails) {
    private var _binding: FragmentCocktailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CocktailAdapter
    private lateinit var list: MutableList<Cocktail>
    private lateinit var cocktailViewModel: CocktailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailsBinding.inflate(inflater, container, false)

        cocktailViewModel = ViewModelProvider(this)[CocktailViewModel::class.java]

        cocktailViewModel.getCocktailList.observe(viewLifecycleOwner) { cocktailModels ->
            when (cocktailModels) {
                is Resource.Success -> {
                    list = cocktailModels.data.toMutableList()
                    if(list.isEmpty()){
                        binding.rViewCocktails.visibility = View.GONE
                        binding.indeterminateBar.visibility = View.GONE
                        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
                        builder
                            .setMessage("No results found")
                            .setTitle("Search Error")
                            .setPositiveButton("OK") { dialog, which ->
                                // Do something.
                            }

                        val dialog: AlertDialog = builder.create()
                        dialog.show()
                        Log.e("Cocktail", "Error")

                    }
                    binding.rViewCocktails.visibility = View.VISIBLE
                    binding.indeterminateBar.visibility = View.GONE
                    rvSetup()
                    Log.e("Cocktail", "CocktailList" + cocktailModels)
                }
                is Resource.Loading -> {
                    list = mutableListOf<Cocktail>()
                    binding.rViewCocktails.visibility = View.GONE
                    binding.indeterminateBar.visibility = View.VISIBLE
                    Log.e("Cocktail", "Empty" + cocktailModels)
                }
                is Resource.Error -> {
                    list = mutableListOf<Cocktail>()
                    binding.rViewCocktails.visibility = View.GONE
                    binding.indeterminateBar.visibility = View.GONE
                    val builder: AlertDialog.Builder = AlertDialog.Builder(context)
                    builder
                        .setMessage("Check your Internet connection")
                        .setTitle("Network Error")
                        .setPositiveButton("OK") { dialog, which ->
                            // Do something.
                        }

                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                    Log.e("Cocktail", "Error")
                }

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