package com.example.levi9application.repositories

import androidx.lifecycle.LiveData
import com.example.levi9application.models.Cocktail
import com.example.levi9application.database.CocktailDAO
import javax.inject.Inject

class CocktailDataRepo @Inject constructor(private val cocktailDAO: CocktailDAO) {
    val readCocktailData: LiveData<List<Cocktail>> = cocktailDAO.readCocktailData()
    suspend fun addCocktail(cocktail: Cocktail) {
        cocktailDAO.insertCocktail(cocktail)
    }

    suspend fun removeCocktail(cocktail: Int) {
        cocktailDAO.deleteCocktail(cocktail)
    }

    suspend fun getFavoriteIds(): List<Int>{
        return cocktailDAO.readFavoriteId()
    }
}