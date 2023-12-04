package com.example.levi9application.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levi9application.model.Cocktail
import com.example.levi9application.model.Resource
import com.example.levi9application.repositories.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailViewModel
@Inject
constructor
    (private val mainRepo: MainRepo): ViewModel() {

    private val _response = MutableLiveData<Resource<List<Cocktail>>>()
    val getCocktailList: MutableLiveData<Resource<List<Cocktail>>>
        get() = _response

    init{
        getCocktails()
    }

    private fun getCocktails() = viewModelScope.launch {
        _response.value = Resource.Loading()

        mainRepo.getCocktailList().let {response ->
            delay(5000)
            if(response.isSuccessful && response.body()!=null){
                val drinks = response.body()!!.cocktails ?: emptyList()
                _response.value = Resource.Success(drinks)
            }
            else{
                _response.value = Resource.Error("Error")
                Log.d("Error","getCocktailList Error: ${response.code()}")
            }
        }
    }

}