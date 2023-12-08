package com.example.levi9application.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.levi9application.model.Cocktail
import com.example.levi9application.model.CocktailDatabase
import com.example.levi9application.repositories.CocktailDataRepo
import com.example.levi9application.view.CocktailDAO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel
@Inject constructor(private val dao: CocktailDAO, application: Application) : ViewModel() {
    private val readDataNonAlc: LiveData<List<Cocktail>>
    private val readDataAlc: LiveData<List<Cocktail>>
    private val readData: LiveData<List<Cocktail>>
    private val repository: CocktailDataRepo

    init {
        val cocktailDAO = CocktailDatabase.getDatabase(application).getDao()
        repository = CocktailDataRepo(cocktailDAO)
        readDataAlc = repository.readDataAlc
        readDataNonAlc = repository.readDataNonAlc
        readData = repository.readData
    }

    fun getListAlc() : List<Cocktail>? {
        return readDataAlc.value
    }

    fun getListNonAlc() : List<Cocktail>? {
        return readDataNonAlc.value
    }

    fun getAll() : List<Cocktail>?{
        return readData.value
    }

}