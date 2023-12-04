package com.example.levi9application.repositories

import com.example.levi9application.network.APIService
import javax.inject.Inject

class MainRepo
@Inject
    constructor(private val apiService: APIService) {
    suspend fun getCocktailList() = apiService.getCocktailList()

    companion object {
        private const val TAG = "MainRepo"
    }

}