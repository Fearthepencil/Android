package com.example.levi9application.models

import com.google.gson.annotations.SerializedName

data class DetailsResponse(
    @SerializedName("drinks")
    val cocktails: ArrayList<CocktailDetail>?
)