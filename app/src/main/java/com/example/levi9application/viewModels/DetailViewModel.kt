package com.example.levi9application.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levi9application.models.CocktailDetail
import com.example.levi9application.models.Resource
import com.example.levi9application.repositories.CocktailDataRepo
import com.example.levi9application.repositories.CocktailRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val cocktailsRepo: CocktailRepo,
    private val cocktailDataRepo: CocktailDataRepo,
) : ViewModel() {


    private var _getCocktailDetails: MutableLiveData<Resource<CocktailDetail>> = MutableLiveData()
    val getCocktailDetails: LiveData<Resource<CocktailDetail>> get() = _getCocktailDetails
    private lateinit var _favorite: LiveData<Boolean>
    val favorite: LiveData<Boolean> get() = _favorite

    fun checkFavorite(cocktailID: String, userEmail: String){
        _favorite = cocktailDataRepo.getFavorite(cocktailID,userEmail)
    }

    fun getDetails(cocktailID: String){
        viewModelScope.launch {
            val response = cocktailsRepo.getDetails(cocktailID)

            if (response.isSuccessful) {
                val cocktails = response.body()?.cocktails ?: emptyList()
                val cocktail = cocktails[0]
                cocktail.string = ingredientSetup(cocktail)
                _getCocktailDetails.value = Resource.Success(cocktail)
            } else {
                _getCocktailDetails.value = Resource.Error("Error")
            }

        }
    }

    private fun ingredientSetup(cocktail: CocktailDetail): String{
        var ingredientMeasureString = ""
        var i = 1
        while(i<16){
            val ingredient = when(i){
                1 -> cocktail.ingredient1
                2 -> cocktail.ingredient2
                3 -> cocktail.ingredient3
                4 -> cocktail.ingredient4
                5 -> cocktail.ingredient5
                6 -> cocktail.ingredient6
                7 -> cocktail.ingredient7
                8 -> cocktail.ingredient8
                9 -> cocktail.ingredient9
                10 -> cocktail.ingredient10
                11 -> cocktail.ingredient11
                12 -> cocktail.ingredient12
                13 -> cocktail.ingredient13
                14 -> cocktail.ingredient14
                15 -> cocktail.ingredient15
                else -> null
            }
            val measure = when(i){
                1 -> cocktail.measure1
                2 -> cocktail.measure2
                3 -> cocktail.measure3
                4 -> cocktail.measure4
                5 -> cocktail.measure5
                6 -> cocktail.measure6
                7 -> cocktail.measure7
                8 -> cocktail.measure8
                9 -> cocktail.measure9
                10 -> cocktail.measure10
                11 -> cocktail.measure11
                12 -> cocktail.measure12
                13 -> cocktail.measure13
                14 -> cocktail.measure14
                15 -> cocktail.measure15
                else -> null
            }

            if(!ingredient.isNullOrBlank() && !measure.isNullOrBlank()){
                val value = "$ingredient $measure\n"
                ingredientMeasureString+=value
            }

            i++
        }

        return ingredientMeasureString

    }
}