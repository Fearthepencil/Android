package com.example.levi9application.repositories

import com.example.levi9application.models.CategoriesResponse
import retrofit2.Response

interface CategoryRepoInterface {
    suspend fun getCategoryList(queries: Map<String,String>): Response<CategoriesResponse>

}