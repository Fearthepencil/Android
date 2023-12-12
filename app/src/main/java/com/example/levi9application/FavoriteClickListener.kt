package com.example.levi9application

import com.example.levi9application.model.Cocktail

interface FavoriteClickListener {
    fun onFavoriteClick(data: Cocktail)
}