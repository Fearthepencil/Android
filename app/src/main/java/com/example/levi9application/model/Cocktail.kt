package com.example.levi9application.model

import com.google.gson.annotations.SerializedName

data class Cocktail(
    @SerializedName("strDrink")
    val title: String? = null,
    @SerializedName("strDrinkThumb")
    val imageSrc: String? = null,
    @SerializedName("strAlcoholic")
    val alcoholic: String? = null,
    @SerializedName("idDrink")
    val id: Int? = null
)
