package com.example.levi9application.repositories

import com.example.levi9application.models.CocktailsResponse
import com.example.levi9application.models.DetailsResponse
import retrofit2.Response

interface CocktailRepoInterface {
    suspend fun getCocktailList(query: String = ""): Response<CocktailsResponse>

    suspend fun getDetails(id: String) : Response<DetailsResponse>

}