package com.example.levi9application.models

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("drinks")
    val categories: ArrayList<Category>
)