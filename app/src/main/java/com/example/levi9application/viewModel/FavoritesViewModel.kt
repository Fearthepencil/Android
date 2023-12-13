package com.example.levi9application.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.levi9application.model.FavoriteItem
import com.example.levi9application.repositories.CocktailDataRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel
@Inject constructor(cocktailsRepo: CocktailDataRepo) : ViewModel() {

    val favoriteItemLiveData: LiveData<List<FavoriteItem>> =
        cocktailsRepo.readCocktailData.map { cocktails ->
            val favItemsList = mutableListOf<FavoriteItem>()
            var i = 0
            if (cocktails.isEmpty()) return@map favItemsList

            var tempLabel = cocktails[i].alcoholic?.let {
                FavoriteItem.LabelItem(it)
            } ?: FavoriteItem.LabelItem("")

            favItemsList.add(tempLabel)

            while (i < cocktails.size) {
                if (tempLabel.title == cocktails[i].alcoholic) {
                    val tempItem =
                        cocktails[i].title?.let {
                            cocktails[i].imageSrc?.let { it1 ->
                                cocktails[i].id?.let { it2 ->
                                    FavoriteItem.Favorite(
                                        it,
                                        it1, it2
                                    )
                                }
                            }
                        }
                    if (tempItem != null) {
                        favItemsList.add(tempItem)
                        i++
                    }
                } else {
                    tempLabel = cocktails[i].alcoholic?.let {
                        FavoriteItem.LabelItem(it)
                    } ?: FavoriteItem.LabelItem("")
                    favItemsList.add(tempLabel)
                }
            }
            return@map favItemsList
        }


}