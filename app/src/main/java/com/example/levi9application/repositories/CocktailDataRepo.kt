package com.example.levi9application.repositories

import androidx.lifecycle.LiveData
import com.example.levi9application.model.Cocktail
import com.example.levi9application.view.CocktailDAO
import javax.inject.Inject

class CocktailDataRepo @Inject constructor(private val cocktailDAO: CocktailDAO) {
    val readDataAlc : LiveData<List<Cocktail>> = cocktailDAO.readDataAlc()
    val readDataNonAlc : LiveData<List<Cocktail>> = cocktailDAO.readDataNonAlc()
    val readData: LiveData<List<Cocktail>> = cocktailDAO.readData()
    suspend fun addCocktail(cocktail: Cocktail){
        cocktailDAO.insertCocktail(cocktail)
    }

    suspend fun removeCocktail(cocktail: Int){
        cocktailDAO.deleteCocktail(cocktail)
    }
}