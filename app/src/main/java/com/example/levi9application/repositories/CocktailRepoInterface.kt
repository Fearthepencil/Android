package com.example.levi9application.repositories

import com.example.levi9application.model.CocktailsResponse
import retrofit2.Response

interface CocktailRepoInterface {
    suspend fun getCocktailList(query: String = "") : Response<CocktailsResponse>

}