package com.example.levi9application.view

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.levi9application.databinding.ItemFavoritesBinding
import com.example.levi9application.databinding.ItemLabelBinding
import com.example.levi9application.model.FavoriteRecyclerViewItem
import com.squareup.picasso.Picasso

sealed class FavoritesHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    class CardViewHolder(private val binding: ItemFavoritesBinding) : FavoritesHolder(binding){
        fun bind(favorite: FavoriteRecyclerViewItem.Cocktail){
            binding.favoritesTitle.text =  favorite.title
            Picasso.get().load(favorite.imageSource)
                .into(binding.favoritesImage)
        }
    }

    class LabelViewHolder(private val binding: ItemLabelBinding) : FavoritesHolder(binding){
        fun bind(title: FavoriteRecyclerViewItem.Label){
            binding.labelText.text = title.title
        }
    }
}