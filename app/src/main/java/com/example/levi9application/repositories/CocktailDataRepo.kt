package com.example.levi9application.repositories

import androidx.lifecycle.LiveData
import com.example.levi9application.models.Cocktail
import com.example.levi9application.database.CocktailDAO
import javax.inject.Inject

class CocktailDataRepo @Inject constructor(private val cocktailDAO: CocktailDAO) {

    suspend fun addCocktail(cocktail: Cocktail) {
        cocktailDAO.insertCocktail(cocktail)
    }

    suspend fun removeCocktail(cocktail: Cocktail) {
        cocktailDAO.deleteCocktail(cocktail)
    }

    suspend fun getFavoriteIds(email: String): List<Int>{
        return cocktailDAO.readFavoriteId(email)
    }

    fun getFavorites(email: String):LiveData<List<Cocktail>>{
        return cocktailDAO.readCocktailData(email)
    }
}