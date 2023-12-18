package com.example.levi9application.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.levi9application.databinding.ItemFilterDetailBinding
import com.example.levi9application.models.Category

class FilterDetailAdapter(
    private var categories: MutableList<Category>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<FilterDetailAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val itemFilterBinding: ItemFilterDetailBinding) :
        RecyclerView.ViewHolder(itemFilterBinding.root) {

        init {
            itemFilterBinding.root.setOnClickListener {
                listener.onItemClick(categories[bindingAdapterPosition])
            }
        }

        fun bind(category: Category) {
            if (category.category != null) itemFilterBinding.filterDetailTitle.text = category.category
            if (category.glass != null) itemFilterBinding.filterDetailTitle.text = category.glass
            if (category.ingredient != null) itemFilterBinding.filterDetailTitle.text = category.ingredient
            if (category.alcoholic != null) itemFilterBinding.filterDetailTitle.text = category.alcoholic
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemFilterDetailBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    interface OnItemClickListener {
        fun onItemClick(category: Category)
    }


}
