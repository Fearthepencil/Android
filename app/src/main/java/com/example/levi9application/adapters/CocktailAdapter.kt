package com.example.levi9application.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.levi9application.R
import com.example.levi9application.databinding.ItemCocktailBinding
import com.example.levi9application.models.Cocktail
import com.squareup.picasso.Picasso

class CocktailAdapter(
    private var cocktails: MutableList<Cocktail>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<CocktailAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(private val itemCocktailBinding: ItemCocktailBinding) :
        RecyclerView.ViewHolder(itemCocktailBinding.root) {

        init {
            itemCocktailBinding.toggle.setOnClickListener {
                listener.onItemClick(cocktails[bindingAdapterPosition])
                if (cocktails[bindingAdapterPosition].selected == true) {
                    itemCocktailBinding.toggle.setImageResource(R.drawable.toggle_button_on)
                } else {
                    itemCocktailBinding.toggle.setImageResource(R.drawable.toggle_button_off)
                }
            }
        }

        fun bind(cocktail: Cocktail) {
            if (cocktail.selected == true) {
                itemCocktailBinding.toggle.setImageResource(R.drawable.toggle_button_on)
            } else {
                itemCocktailBinding.toggle.setImageResource(R.drawable.toggle_button_off)
            }
            itemCocktailBinding.cardTitle.text = cocktail.title
            Picasso.get().load(cocktail.imageSrc).into(itemCocktailBinding.cocktailImage)

        }
    }


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
        holder.bind(cocktails[position])
    }

    interface OnItemClickListener {
        fun onItemClick(cocktail: Cocktail)
    }


}
