package com.example.levi9application

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.levi9application.databinding.FragmentFavoritesBinding
import com.example.levi9application.model.FavoriteRecyclerViewItem
import com.example.levi9application.viewModel.FavoritesViewModel


class FavoritesFragment : Fragment(R.layout.fragment_favorites){
    private lateinit var _binding: FragmentFavoritesBinding
    private lateinit var viewModel: FavoritesViewModel
    private lateinit var _adapter: FavoritesAdapter
    private lateinit var list: MutableList<FavoriteRecyclerViewItem>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[FavoritesViewModel::class.java]
        list = mutableListOf()
        setList()
        val layoutManager = GridLayoutManager(context, 2)
        _binding.rViewFavorites.layoutManager = layoutManager
        _adapter = FavoritesAdapter(list)
        _binding.rViewFavorites.apply{
            adapter = _adapter
        }
        return _binding.root
    }

    private fun setList(){
        list.add(FavoriteRecyclerViewItem.Label("Alchoholic"))
        for(i in 1..4) list.add(FavoriteRecyclerViewItem.Cocktail("Drinkic","https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/2x8thr1504816928.jpg"))
        list.add(FavoriteRecyclerViewItem.Label("Non-Alchoholic"))
        for(i in 1..4) list.add(FavoriteRecyclerViewItem.Cocktail("Drinkic","https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/2x8thr1504816928.jpg"))
    }


}