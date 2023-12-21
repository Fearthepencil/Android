package com.example.levi9application.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.levi9application.MainActivity
import com.example.levi9application.R
import com.example.levi9application.adapters.FavoritesAdapter
import com.example.levi9application.databinding.FragmentFavoritesBinding
import com.example.levi9application.models.FavoriteItem
import com.example.levi9application.viewModels.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {
    private lateinit var _binding: FragmentFavoritesBinding
    private lateinit var viewModel: FavoritesViewModel
    private lateinit var _adapter: FavoritesAdapter
    private lateinit var list: List<FavoriteItem>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[FavoritesViewModel::class.java]

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = GridLayoutManager(context, 2)

        list = mutableListOf()
        _binding.rViewFavorites.layoutManager = layoutManager
        _adapter = FavoritesAdapter(list)
        _binding.rViewFavorites.apply {
            adapter = _adapter
        }
        viewModel.getFavoriteItemLiveData((activity as MainActivity).email)
        viewModel.favoriteItemLiveData.observe(viewLifecycleOwner) {
            _adapter.setData(it)
            this.list = it
        }
    }





}