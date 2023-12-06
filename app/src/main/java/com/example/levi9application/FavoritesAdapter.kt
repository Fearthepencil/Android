package com.example.levi9application

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.levi9application.databinding.ItemFavoritesBinding
import com.example.levi9application.databinding.ItemLabelBinding
import com.example.levi9application.model.FavoriteRecyclerViewItem
import com.example.levi9application.view.FavoritesHolder

class FavoritesAdapter(private var items: MutableList<FavoriteRecyclerViewItem>) :
    RecyclerView.Adapter<FavoritesHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesHolder {
        return when (viewType) {
            R.layout.item_favorites -> FavoritesHolder.CardViewHolder(
                ItemFavoritesBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            R.layout.item_label -> FavoritesHolder.LabelViewHolder(
                ItemLabelBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            else -> throw IllegalArgumentException("Invalid Type")
        }
    }

    override fun onBindViewHolder(holder: FavoritesHolder, position: Int) {
        when(holder){
            is FavoritesHolder.CardViewHolder -> holder.bind(items[position] as FavoriteRecyclerViewItem.Cocktail)
            is FavoritesHolder.LabelViewHolder -> holder.bind(items[position] as FavoriteRecyclerViewItem.Label)
        }

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is FavoriteRecyclerViewItem.Cocktail -> R.layout.item_favorites
            is FavoriteRecyclerViewItem.Label -> R.layout.item_label
        }
    }

}