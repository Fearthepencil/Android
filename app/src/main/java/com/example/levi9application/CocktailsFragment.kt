package com.example.levi9application

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
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
    private var query: String = ""
    private val binding get() = _binding!!
    private lateinit var adapter: CocktailAdapter
    private lateinit var list: MutableList<Cocktail>
    private lateinit var cocktailViewModel: CocktailViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailsBinding.inflate(inflater, container, false)

        cocktailViewModel = ViewModelProvider(this)[CocktailViewModel::class.java]


        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.menuSearch -> {
                        if (binding.etSearch.visibility != View.VISIBLE) binding.etSearch.visibility =
                            View.VISIBLE
                        else binding.etSearch.visibility = View.GONE
                    }

                    R.id.menuFilter -> Toast.makeText(
                        activity, "Clicked on Filter", Toast.LENGTH_SHORT
                    ).show()
                }
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding.etSearch.doAfterTextChanged {
            query = it.toString().trim()
            cocktailViewModel.getCocktails(query)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etSearch.visibility = View.GONE
        cocktailViewModel.getCocktailList.observe(viewLifecycleOwner) { cocktailModels ->
            when (cocktailModels) {
                is Resource.Success -> {
                    list = cocktailModels.data.toMutableList()
                    if (list.isEmpty()) {
                        binding.rViewCocktails.visibility = View.GONE
                        binding.indeterminateBar.visibility = View.GONE
                        showDialog("No results found", "Search Error")
                        Log.e("Cocktail", "Empty List")

                    }
                    binding.rViewCocktails.visibility = View.VISIBLE
                    binding.indeterminateBar.visibility = View.GONE
                    rvSetup()
                    Log.e("Cocktail", "CocktailList$cocktailModels")
                }

                is Resource.Loading -> {
                    list = mutableListOf()
                    binding.rViewCocktails.visibility = View.GONE
                    binding.indeterminateBar.visibility = View.VISIBLE
                }

                is Resource.Error -> {
                    list = mutableListOf()
                    binding.rViewCocktails.visibility = View.GONE
                    binding.indeterminateBar.visibility = View.GONE
                }

            }
        }
        cocktailViewModel.getCocktails()

    }

    private fun rvSetup() {

        adapter = CocktailAdapter(list)

        binding.rViewCocktails.adapter = adapter

        val layoutManager = GridLayoutManager(context, 2)
        binding.rViewCocktails.layoutManager = layoutManager


    }

    private fun showDialog(message: String, title: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setMessage(message).setTitle(title).setPositiveButton("OK") { _, _ ->
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


}