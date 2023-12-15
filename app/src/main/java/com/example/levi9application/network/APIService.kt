package com.example.levi9application.network

import com.example.levi9application.models.CategoriesResponse
import com.example.levi9application.models.CocktailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface APIService {
    @GET("search.php")
    suspend fun getCocktailList(@Query("s") query: String = ""): Response<CocktailsResponse>

    @GET
    suspend fun getCategoryList(@Url url: String): Response<CategoriesResponse>

}