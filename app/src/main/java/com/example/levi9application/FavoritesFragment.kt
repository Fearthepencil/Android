package com.example.levi9application

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
        _adapter = FavoritesAdapter(list)
        _binding.rViewFavorites.apply{
            adapter = _adapter
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}