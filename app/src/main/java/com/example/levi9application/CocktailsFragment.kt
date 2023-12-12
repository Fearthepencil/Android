package com.example.levi9application

import android.app.AlertDialog
import android.os.Bundle
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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.levi9application.databinding.FragmentCocktailsBinding
import com.example.levi9application.model.Cocktail
import com.example.levi9application.model.Resource
import com.example.levi9application.viewModel.CocktailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CocktailsFragment : Fragment(R.layout.fragment_cocktails), FavoriteClickListener {
    private var _binding: FragmentCocktailsBinding? = null
    private var query: String = ""
    private val binding get() = _binding!!
    private lateinit var adapter: CocktailAdapter
    private lateinit var list: MutableList<Cocktail>
    private lateinit var cocktailViewModel: CocktailViewModel
    private lateinit var _inflater: LayoutInflater
    private lateinit var _container: ViewGroup


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _inflater = inflater
        if (container != null) {
            _container = container
        }
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
                        showDialog(
                            resources.getString(R.string.searchErrorMessage),
                            resources.getString(R.string.searchErrorTitle)
                        )

                    }
                    rvSetup()
                    binding.rViewCocktails.visibility = View.VISIBLE
                    binding.indeterminateBar.visibility = View.GONE
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
        binding.etSearch.doAfterTextChanged {
            query = it.toString().trim()
            cocktailViewModel.getCocktails(query)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            query = binding.etSearch.text.toString()
            cocktailViewModel.getCocktails(query)
        }
    }

    private fun rvSetup() {
        checkIfFavorite()

        adapter = CocktailAdapter(list, this)

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


    override fun onFavoriteClick(data: Cocktail) {
        for (cocktail in list) {
            if (cocktail.id == data.id) {
                if (cocktail.selected == true) {
                    cocktail.selected = false
                    data.id?.let {
                        cocktailViewModel.deleteCocktail(it)
                    }
                } else {
                    cocktail.selected = true
                    cocktailViewModel.addCocktail(data)
                }
                break
            }
        }

    }

    private fun checkIfFavorite(){
        cocktailViewModel.readData.observe(viewLifecycleOwner, Observer {favorites ->
            for(favorite in favorites){
                for(cocktail in list){
                    if(favorite.id == cocktail.id){
                        cocktail.selected = true
                    }
                }
            }
        })

    }


}