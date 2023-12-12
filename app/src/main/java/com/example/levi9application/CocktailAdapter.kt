package com.example.levi9application

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.levi9application.databinding.ItemCocktailBinding
import com.example.levi9application.model.Cocktail
import com.squareup.picasso.Picasso

class CocktailAdapter(
    private var cocktails: MutableList<Cocktail>,
    private var listener: FavoriteClickListener
) :
    RecyclerView.Adapter<CocktailAdapter.ItemViewModel>() {


    private var favoritesList: ArrayList<Cocktail> = ArrayList()

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
        if (cocktails[position].selected == true) {
            holder.itemCocktailBinding.toggle.setImageResource(R.drawable.toggle_button_on)
            holder.itemCocktailBinding.toggle.tag = "1"
        } else {
            holder.itemCocktailBinding.toggle.setImageResource(R.drawable.toggle_button_off)
            holder.itemCocktailBinding.toggle.tag = "0"
        }
        holder.itemCocktailBinding.toggle.setOnClickListener() {
            listener.onFavoriteClick(cocktails[position])
            if (holder.itemCocktailBinding.toggle.tag == "0") {
                holder.itemCocktailBinding.toggle.setImageResource(R.drawable.toggle_button_on)
                Log.e("checked", "AAA")
                holder.itemCocktailBinding.toggle.tag = "1"
            } else {
                holder.itemCocktailBinding.toggle.setImageResource(R.drawable.toggle_button_off)
                Log.e("unchecked", "AAA")
                holder.itemCocktailBinding.toggle.tag = "0"
            }

        }


    }


}
