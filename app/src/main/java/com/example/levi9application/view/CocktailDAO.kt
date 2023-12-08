package com.example.levi9application.view

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.levi9application.model.Cocktail

@Dao
interface CocktailDAO {
    @Upsert
    suspend fun insertCocktail(cocktail: Cocktail)

    @Query("DELETE FROM cocktail_table WHERE id = :cocktailId")
    suspend fun deleteCocktail(cocktailId: Int)

    @Query("SELECT id,alcoholic,imageSrc,title FROM cocktail_table WHERE alcoholic='Alcoholic' ORDER BY id ASC")
    fun readDataAlc(): LiveData<List<Cocktail>>

    @Query("SELECT id,alcoholic,imageSrc,title FROM cocktail_table WHERE alcoholic='Non alcoholic' ORDER BY id ASC")
    fun readDataNonAlc(): LiveData<List<Cocktail>>

    @Query("SELECT * FROM cocktail_table ORDER BY id ASC")
    fun readData(): LiveData<List<Cocktail>>
}