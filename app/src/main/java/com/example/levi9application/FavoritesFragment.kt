package com.example.levi9application

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.levi9application.databinding.FragmentFavoritesBinding
import com.example.levi9application.model.FavoriteItem
import com.example.levi9application.viewModel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites){
    private lateinit var _binding: FragmentFavoritesBinding
    private lateinit var viewModel: FavoritesViewModel
    private lateinit var _adapter: FavoritesAdapter
    private lateinit var list: MutableList<FavoriteItem>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[FavoritesViewModel::class.java]
        val layoutManager = GridLayoutManager(context, 2)

        setList()

        _binding.rViewFavorites.layoutManager = layoutManager
        _adapter = FavoritesAdapter(list)
        _binding.rViewFavorites.apply{
            adapter = _adapter
        }
        getData()

        return _binding.root
    }

    private fun setList(){
        list = mutableListOf()
    }
    private fun getData(){
        viewModel.readData.observe(viewLifecycleOwner,Observer{ favorite ->
            _adapter.setData(favorite)
        })
//
//        val tempListAlc = viewModel.getListAlc()
//        val tempListNonAlc = viewModel.getListNonAlc()
//
//        if (tempListAlc != null) {
//            for(cocktail in tempListAlc){
//                list.add(FavoriteItem.Favorite(cocktail.title!!,cocktail.imageSrc!!,cocktail.id!!))
//                Log.e("msg","${cocktail.id}"+cocktail.title+cocktail.imageSrc)
//            }
//        }
//        list.add(FavoriteItem.LabelItem("Non Alcoholic"))
//        if (tempListNonAlc != null) {
//            for(cocktail in tempListNonAlc){
//                list.add(FavoriteItem.Favorite(cocktail.title!!,cocktail.imageSrc!!,cocktail.id!!))
//            }
//        }
//        val tempList = viewModel.getAll()
    }


}