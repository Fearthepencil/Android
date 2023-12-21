package com.example.levi9application.viewModels

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levi9application.models.Cocktail
import com.example.levi9application.models.Resource
import com.example.levi9application.repositories.CocktailDataRepo
import com.example.levi9application.repositories.CocktailRepo
import com.example.levi9application.repositories.FilterRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailViewModel
@Inject constructor(
    private val cocktailRepo: CocktailRepo,
    private val filterRepo: FilterRepo,
    cocktailDataRepo: CocktailDataRepo,
    private val sharedPreferences: SharedPreferences
) :
    ViewModel() {

    private var job: Job? = null
    private val _repository: CocktailDataRepo
    private val _response = MutableLiveData<Resource<List<Cocktail>>>()
    val getCocktailList: LiveData<Resource<List<Cocktail>>>
        get() = _response
    private val debounce = 500L

    private val handler = CoroutineExceptionHandler { _, e ->
        _response.value = e.message?.let { Resource.Error(it) }
    }


    init {
        _repository = cocktailDataRepo
    }

    fun getCocktails(query: String = "", email: String) {
        job?.cancel()
        job = viewModelScope.launch(handler) {
            _response.value = Resource.Loading()
            if (query.isNotEmpty()) {
                delay(debounce)
            }
            val response = cocktailRepo.getCocktailList(query)
            if (response.isSuccessful) {
                val drinks = response.body()?.cocktails ?: emptyList()
                val favorites = sharedPreferences.getString("${email}_email",null)?.let {
                        _repository.getFavoriteIds(it)
                }
                for (cocktail in drinks) {
                    if (favorites != null) {
                        cocktail.selected = favorites.contains(cocktail.id)
                    }
                }
                _response.value = Resource.Success(drinks)
            } else {
                _response.value = Resource.Error(response.message())
            }
        }

    }

    fun getFilteredCocktails(queries: Map<String, String>) {
        viewModelScope.launch(handler) {
            _response.value = Resource.Loading()
            val response = filterRepo.getFilterList(queries)
            if (response.isSuccessful) {
                val drinks = response.body()?.cocktails ?: emptyList()
                val favorites = sharedPreferences.getString("_email",null)?.let {
                    _repository.getFavoriteIds(it)
                }
                for (cocktail in drinks) {
                    if (favorites != null) {
                        cocktail.selected = favorites.contains(cocktail.id)
                    }
                }
                _response.value = Resource.Success(drinks)
            } else {
                _response.value = Resource.Error(response.message())
            }
        }
    }

    fun addCocktail(cocktail: Cocktail, email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            cocktail.email = sharedPreferences.getString("${email}_email",null).toString()
            _repository.addCocktail(cocktail)
        }
    }

    fun deleteCocktail(cocktail: Cocktail, email:String) {
        viewModelScope.launch(Dispatchers.IO) {
            cocktail.email = sharedPreferences.getString("${email}_email",null).toString()
            _repository.removeCocktail(cocktail)
        }
    }



}
