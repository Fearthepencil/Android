package com.example.levi9application

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.levi9application.databinding.ItemFavoritesBinding
import com.example.levi9application.databinding.ItemLabelBinding
import com.example.levi9application.model.Cocktail
import com.example.levi9application.model.FavoriteItem
import com.example.levi9application.view.FavoritesHolder

class FavoritesAdapter(private var items: MutableList<FavoriteItem>) :
    RecyclerView.Adapter<FavoritesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesHolder {
        return when (viewType) {
            FavoriteItem.Type.FAVORITE.ordinal -> FavoritesHolder.CardViewHolder(
                ItemFavoritesBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            FavoriteItem.Type.LABEL.ordinal -> FavoritesHolder.LabelViewHolder(
                ItemLabelBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            else -> throw IllegalArgumentException("Invalid Type")
        }
    }

    override fun onBindViewHolder(holder: FavoritesHolder, position: Int) {
        when (holder) {
            is FavoritesHolder.CardViewHolder -> holder.bind(items[position] as FavoriteItem.Favorite)
            is FavoritesHolder.LabelViewHolder -> holder.bind(items[position] as FavoriteItem.LabelItem)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int = items[position].getType()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        (recyclerView.layoutManager as GridLayoutManager).spanSizeLookup = getSpanSizeLookup()
    }

    private fun getSpanSizeLookup(): GridLayoutManager.SpanSizeLookup {
        return object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (getItemViewType(position)) {
                    FavoriteItem.Type.FAVORITE.ordinal -> 1
                    FavoriteItem.Type.LABEL.ordinal -> 2
                    else -> 1
                }
            }
        }
    }

    fun setData(cocktails: List<Cocktail>) {
        this.items = adapt(cocktails)
        notifyDataSetChanged()
    }

    private fun adapt(cocktails: List<Cocktail>): MutableList<FavoriteItem> {
        val list = mutableListOf<FavoriteItem>()
        list.add(FavoriteItem.LabelItem("Alcoholic"))
        for (cocktail in cocktails) {
            cocktail.imageSrc?.let {
                cocktail.id?.let { it1 ->
                    cocktail.title?.let { it2 ->
                        FavoriteItem.Favorite(
                            it2,
                            it, it1
                        )
                    }
                }
            }?.let { list.add(it) }
        }
        return list
    }

}