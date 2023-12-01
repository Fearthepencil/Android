package com.example.levi9application.Model

import com.google.gson.annotations.SerializedName

data class Drinks(
    @SerializedName("drinks")
    val cocktails: ArrayList<Cocktail>
)
