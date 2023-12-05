package com.example.levi9application

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.levi9application.databinding.ItemCocktailBinding
import com.example.levi9application.model.Cocktail
import com.squareup.picasso.Picasso

class CocktailAdapter(private var cocktails: MutableList<Cocktail>) :
    RecyclerView.Adapter<CocktailAdapter.ItemViewModel>() {
    inner class ItemViewModel(val itemCocktailBinding: ItemCocktailBinding) :
        RecyclerView.ViewHolder(itemCocktailBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewModel {
        return ItemViewModel(
            ItemCocktailBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return cocktails.size
    }

    override fun onBindViewHolder(holder: ItemViewModel, position: Int) {
        Picasso.get().load(cocktails[position].imageSrc)
            .into(holder.itemCocktailBinding.cocktailImage)
        holder.itemCocktailBinding.cardTitle.text = cocktails[position].title
    }

}