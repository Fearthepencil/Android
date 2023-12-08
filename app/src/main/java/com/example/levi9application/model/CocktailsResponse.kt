package com.example.levi9application.model

import com.google.gson.annotations.SerializedName

data class CocktailsResponse(
    @SerializedName("drinks")
    val cocktails: ArrayList<Cocktail>
)
