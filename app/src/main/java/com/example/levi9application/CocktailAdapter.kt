package com.example.levi9application

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.levi9application.databinding.ItemCocktailBinding
import com.example.levi9application.model.Cocktail
import com.example.levi9application.model.FavoriteItem
import com.squareup.picasso.Picasso

class CocktailAdapter(
    private var cocktails: MutableList<Cocktail>,
    private var listener: FavoriteClickListener,
    private inline val onItemClicked: ((FavoriteItem, Int) -> Unit)? = null
) :
    RecyclerView.Adapter<CocktailAdapter.ItemViewHolder>() {


    inner class ItemViewHolder(val itemCocktailBinding: ItemCocktailBinding) :
        RecyclerView.ViewHolder(itemCocktailBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
       return ItemViewHolder(
            ItemCocktailBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )

       )
    }


    override fun getItemCount(): Int {
        return cocktails.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        Picasso.get().load(cocktails[position].imageSrc)
            .into(holder.itemCocktailBinding.cocktailImage)
        holder.itemCocktailBinding.cardTitle.text = cocktails[position].title
        if (cocktails[position].selected == true) {
            holder.itemCocktailBinding.toggle.setImageResource(R.drawable.toggle_button_on)
        } else {
            holder.itemCocktailBinding.toggle.setImageResource(R.drawable.toggle_button_off)
        }
        holder.itemCocktailBinding.toggle.setOnClickListener() {
            listener.onFavoriteClick(cocktails[position])
            if (cocktails[position].selected == true) {
                holder.itemCocktailBinding.toggle.setImageResource(R.drawable.toggle_button_on)
            } else {
                holder.itemCocktailBinding.toggle.setImageResource(R.drawable.toggle_button_off)
            }
        }


    }


}
