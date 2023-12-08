package com.example.levi9application

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.levi9application.databinding.ItemCocktailBinding
import com.example.levi9application.model.Cocktail
import com.example.levi9application.viewModel.CocktailViewModel
import com.squareup.picasso.Picasso

class CocktailAdapter(private var cocktails: MutableList<Cocktail>,private var viewModel: CocktailViewModel) :
    RecyclerView.Adapter<CocktailAdapter.ItemViewModel>() {
    inner class ItemViewModel(val itemCocktailBinding: ItemCocktailBinding) :
        RecyclerView.ViewHolder(itemCocktailBinding.root)
    private val _viewModel = viewModel
    private var onClick = false
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
        holder.itemCocktailBinding.toggle.setOnClickListener{
            if(holder.itemCocktailBinding.toggle.tag == "0" ){
                holder.itemCocktailBinding.toggle.setImageResource(R.drawable.toggle_button_on)
                insertData(cocktails[position],_viewModel)
                Log.e("checked","AAA")
                holder.itemCocktailBinding.toggle.tag = "1"}
            else {
                holder.itemCocktailBinding.toggle.setImageResource(R.drawable.toggle_button_off)
                cocktails[position].id?.let { it1 -> deleteData(it1,_viewModel) }
                Log.e("unchecked","AAA")
                holder.itemCocktailBinding.toggle.tag = "0"
            }
        }

    }


    private fun insertData(cocktail: Cocktail,viewModel: CocktailViewModel){
        viewModel.addCocktail(cocktail)
    }

    private fun deleteData(cocktail: Int, viewModel: CocktailViewModel){
        viewModel.deleteCocktail(cocktail)
    }


}
