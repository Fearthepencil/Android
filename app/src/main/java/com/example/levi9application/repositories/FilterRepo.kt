package com.example.levi9application.repositories

import com.example.levi9application.models.CocktailsResponse
import com.example.levi9application.network.APIService
import retrofit2.Response
import javax.inject.Inject

class FilterRepo @Inject constructor(private val apiService: APIService) : FilterRepoInterface {
    override suspend fun getFilterList(queries: Map<String, String>): Response<CocktailsResponse> = apiService.getFilterList(queries)
}