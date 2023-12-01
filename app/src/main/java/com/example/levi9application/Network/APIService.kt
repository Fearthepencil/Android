package com.example.levi9application.Network

import com.example.levi9application.Model.Cocktail
import com.example.levi9application.Model.Drinks
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("search.php?s=margarita")
    fun getCocktailList() : Call<Drinks>
}