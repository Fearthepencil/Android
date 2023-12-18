package com.example.levi9application.repositories

import com.example.levi9application.models.CategoriesResponse
import com.example.levi9application.network.APIService
import retrofit2.Response
import javax.inject.Inject

class CategoryRepo @Inject constructor(private val apiService: APIService) : CategoryRepoInterface {
    override suspend fun getCategoryList(queries: Map<String,String>): Response<CategoriesResponse> = apiService.getCategoryList(queries)
}