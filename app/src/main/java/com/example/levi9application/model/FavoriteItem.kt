package com.example.levi9application.model

sealed class FavoriteItem{
    abstract fun getType() : Int

    enum class Type{
        FAVORITE,
        LABEL
    }

    data class Favorite(val title: String, val imageSource: String): com.example.levi9application.model.FavoriteItem(){
        override fun getType(): Int = Type.FAVORITE.ordinal
    }

    data class LabelItem(val title: String): com.example.levi9application.model.FavoriteItem(){
        override fun getType(): Int = Type.LABEL.ordinal
    }


}
