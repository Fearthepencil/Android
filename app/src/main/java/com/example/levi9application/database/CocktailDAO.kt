package com.example.levi9application.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.levi9application.models.Cocktail

@Dao
interface CocktailDAO {
    @Upsert
    suspend fun insertCocktail(cocktail: Cocktail)

    @Delete
    suspend fun deleteCocktail(cocktail: Cocktail)

    //read where email = email_param
    @Query("SELECT * FROM cocktail_table WHERE email = :email ORDER BY alcoholic, id ASC")
    fun readCocktailData(email: String): LiveData<List<Cocktail>>


    //read where email = email_param
    @Query("SELECT id FROM cocktail_table WHERE email = :email ORDER BY id ASC")
    suspend fun readFavoriteId(email: String): List<Int>
}