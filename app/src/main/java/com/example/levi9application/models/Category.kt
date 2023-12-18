package com.example.levi9application.models

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("strAlcoholic")
    val alcoholic: String? = null,
    @SerializedName("strIngredient1")
    val ingredient: String? = null,
    @SerializedName("strGlass")
    val glass: String? = null,
    @SerializedName("strCategory")
    val category: String? = null
)