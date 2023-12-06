package com.example.levi9application.model

sealed class FavoriteRecyclerViewItem{
    class Label(
        val title: String
    ): FavoriteRecyclerViewItem()

    class Cocktail(
        val title: String,
        val imageSource: String
    ): FavoriteRecyclerViewItem()

}
