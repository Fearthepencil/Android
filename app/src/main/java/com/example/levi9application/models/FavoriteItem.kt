package com.example.levi9application.models

sealed class FavoriteItem {
    abstract fun getType(): Int

    enum class Type {
        FAVORITE,
        LABEL
    }

    data class Favorite(val title: String, val imageSource: String, val id: Int) :
        FavoriteItem() {
        override fun getType(): Int = Type.FAVORITE.ordinal
    }

    data class LabelItem(val title: String) : FavoriteItem() {
        override fun getType(): Int = Type.LABEL.ordinal
    }


}
