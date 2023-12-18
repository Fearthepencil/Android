package com.example.levi9application.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.levi9application.R
import com.example.levi9application.adapters.CocktailAdapter
import com.example.levi9application.databinding.FragmentCocktailsBinding
import com.example.levi9application.models.Cocktail
import com.example.levi9application.models.Resource
import com.example.levi9application.viewModels.CocktailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CocktailsFragment : Fragment(R.layout.fragment_cocktails) {
    private var _binding: FragmentCocktailsBinding? = null
    private var query: String = ""
    private val args: CocktailsFragmentArgs by navArgs()
    private var queryParams: Map<String, String> = mapOf()
    private val binding get() = _binding!!
    private lateinit var adapter: CocktailAdapter
    private var list: MutableList<Cocktail> = mutableListOf()
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

                    R.id.menuFilter -> {
                        val navigation = findNavController()
                        navigation.navigate(R.id.action_cocktails_to_filterFragment)
                    }
                }
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etSearch.visibility = View.GONE
        if (args.category == "" || args.specificCategory == "") {
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

                        } else {
                            binding.rViewCocktails.visibility = View.VISIBLE
                            binding.indeterminateBar.visibility = View.GONE
                        }
                        rvSetup()
                    }

                    is Resource.Loading -> {
                        binding.rViewCocktails.visibility = View.GONE
                        binding.indeterminateBar.visibility = View.VISIBLE
                    }

                    is Resource.Error -> {
                        binding.rViewCocktails.visibility = View.GONE
                        binding.indeterminateBar.visibility = View.GONE
                    }

                }
            }

            cocktailViewModel.getCocktails()

        } else {
            if(queryParams.isEmpty())
                queryParams = mapOf(
                    args.category to args.specificCategory
                )
            cocktailViewModel.getFilteredCocktails(queryParams)
            cocktailViewModel.getFilterList.observe(viewLifecycleOwner) { cocktailModels ->
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

                        } else {
                            binding.rViewCocktails.visibility = View.VISIBLE
                            binding.indeterminateBar.visibility = View.GONE
                        }
                        rvSetup()
                    }

                    is Resource.Loading -> {
                        binding.rViewCocktails.visibility = View.GONE
                        binding.indeterminateBar.visibility = View.VISIBLE
                    }

                    is Resource.Error -> {
                        binding.rViewCocktails.visibility = View.GONE
                        binding.indeterminateBar.visibility = View.GONE
                    }
                }

            }


        }

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
        adapter = CocktailAdapter(list, viewBindingOnItemClickListener)
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


    private val viewBindingOnItemClickListener = object : CocktailAdapter.OnItemClickListener {
        override fun onItemClick(cocktail: Cocktail) {
            if (cocktail.selected == true) {
                cocktail.selected = false
                cocktail.id?.let {
                    cocktailViewModel.deleteCocktail(it)
                }
            } else {
                cocktail.selected = true
                cocktailViewModel.addCocktail(cocktail)
            }
        }
    }
}

