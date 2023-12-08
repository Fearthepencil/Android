package com.example.levi9application.network

import com.example.levi9application.model.CocktailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("search.php")
    suspend fun getCocktailList(@Query("s")query: String=""): Response<CocktailsResponse>

}