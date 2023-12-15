package com.example.levi9application.repositories

import com.example.levi9application.models.CocktailsResponse
import retrofit2.Response

interface CocktailRepoInterface {
    suspend fun getCocktailList(query: String = ""): Response<CocktailsResponse>

}