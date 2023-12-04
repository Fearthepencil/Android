package com.example.levi9application.network

import com.example.levi9application.model.Drinks
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("search.php?s=margarita")
    suspend fun getCocktailList(): Response<Drinks>

}