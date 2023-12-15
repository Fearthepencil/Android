package com.example.levi9application.repositories

import com.example.levi9application.models.CocktailsResponse
import retrofit2.Response

interface FilterRepoInterface {
    suspend fun getFilterList(queries: Map<String,String>): Response<CocktailsResponse>

}