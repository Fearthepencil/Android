package com.example.levi9application.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levi9application.model.Cocktail
import com.example.levi9application.model.CocktailDatabase
import com.example.levi9application.model.Resource
import com.example.levi9application.repositories.CocktailDataRepo
import com.example.levi9application.repositories.CocktailRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailViewModel
@Inject constructor(private val cocktailRepo: CocktailRepo, application: Application) :
    ViewModel() {

    private var job: Job? = null
    val readData: LiveData<List<Cocktail>>
    private val _repository: CocktailDataRepo
    private val _response = MutableLiveData<Resource<List<Cocktail>>>()
    val getCocktailList: LiveData<Resource<List<Cocktail>>>
        get() = _response

    private val debounce = 500L

    private val handler = CoroutineExceptionHandler { _, e ->
        _response.value = e.message?.let { Resource.Error(it) }
    }

    init {
        getCocktails()
        val cocktailDAO = CocktailDatabase.getDatabase(application).getDao()
        _repository = CocktailDataRepo(cocktailDAO)
        readData = _repository.readCocktailData
    }

    fun getCocktails(query: String = "") {
        job?.cancel()
        job = viewModelScope.launch(handler) {
            _response.value = Resource.Loading()

            if (query.isNotEmpty()) {
                delay(debounce)
            }
            val response = cocktailRepo.getCocktailList(query)
            if (response.isSuccessful) {
                val drinks = response.body()?.cocktails ?: emptyList()
                val favorites = _repository.getFavoriteIds()
                for (cocktail in drinks) {
                    cocktail.selected = favorites.contains(cocktail.id)
                }
                _response.value = Resource.Success(drinks)
            } else {
                _response.value = Resource.Error(response.message())
            }
        }

    }

    fun addCocktail(cocktail: Cocktail) {
        viewModelScope.launch(Dispatchers.IO) {
            _repository.addCocktail(cocktail)
        }
    }

    fun deleteCocktail(cocktail: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _repository.removeCocktail(cocktail)
        }
    }


}
