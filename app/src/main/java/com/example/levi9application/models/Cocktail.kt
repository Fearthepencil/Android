package com.example.levi9application.models

import androidx.room.Entity

import com.google.gson.annotations.SerializedName

@Entity(tableName = "cocktail_table", primaryKeys = ["id", "email"])
data class Cocktail(
    @SerializedName("strDrink")
    val title: String? = null,
    @SerializedName("strDrinkThumb")
    val imageSrc: String? = null,
    @SerializedName("strAlcoholic")
    val alcoholic: String? = null,
    @SerializedName("idDrink")
    val id: Int,
    var selected: Boolean? = false,
    var email: String
)
