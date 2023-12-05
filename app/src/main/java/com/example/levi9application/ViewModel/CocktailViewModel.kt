package com.example.levi9application.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levi9application.model.Cocktail
import com.example.levi9application.model.Resource
import com.example.levi9application.repositories.CocktailRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailViewModel
@Inject
constructor
    (private val cocktailRepo: CocktailRepo) : ViewModel() {

    private var job: Job? = null

    private val _response = MutableLiveData<Resource<List<Cocktail>>>()
    val getCocktailList: MutableLiveData<Resource<List<Cocktail>>>
        get() = _response

    private val debounce = 500L
    init{
        getCocktails()
    }
    fun getCocktails(query: String="") {
        job?.cancel()
        job = viewModelScope.launch{
            try {
                _response.value = Resource.Loading()
                if(query.isNotEmpty()){
                    delay(debounce)
                }
                val response = cocktailRepo.getCocktailList(query)
                if (response.isSuccessful) {
                    val drinks = response.body()?.cocktails ?: emptyList()
                    _response.value = Resource.Success(drinks)
                } else {
                    _response.value = Resource.Error(response.message())
                }
            } catch (e: Exception) {
                _response.value = e.message?.let { Resource.Error(it) }
            }

    }

    }
    }