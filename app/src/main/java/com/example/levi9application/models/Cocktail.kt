package com.example.levi9application.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cocktail_table")
data class Cocktail(
    @SerializedName("strDrink")
    val title: String? = null,
    @SerializedName("strDrinkThumb")
    val imageSrc: String? = null,
    @SerializedName("strAlcoholic")
    val alcoholic: String? = null,
    @SerializedName("idDrink")
    @PrimaryKey
    val id: Int? = null,
    var selected: Boolean? = false,
    var email: String? =null
)
