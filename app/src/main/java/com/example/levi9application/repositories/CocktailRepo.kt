package com.example.levi9application.repositories

import com.example.levi9application.network.APIService
import javax.inject.Inject

class CocktailRepo @Inject constructor(private val apiService: APIService) : CocktailRepoInterface {
   override suspend fun getCocktailList(query: String) = apiService.getCocktailList(query)
}