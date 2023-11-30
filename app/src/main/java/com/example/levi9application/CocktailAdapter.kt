package com.example.levi9application

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.levi9application.databinding.ItemCocktailBinding

class CocktailAdapter(private var cocktails: List<Cocktail>): RecyclerView.Adapter<CocktailAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(val itemCocktailBinding: ItemCocktailBinding) : RecyclerView.ViewHolder(itemCocktailBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemCocktailBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return cocktails.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        Glide.with(holder.itemCocktailBinding.cocktailImage.context)
            .load(cocktails[position].imageSrc)
            .into(holder.itemCocktailBinding.cocktailImage)
       holder.itemCocktailBinding.cardTitle.text = cocktails[position].title
       holder.itemCocktailBinding.toggle.isChecked = cocktails[position].isFavorite
    }

}