package com.example.levi9application.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.levi9application.model.Drinks
import com.example.levi9application.repositories.MainRepo

class CocktailViewModel : ViewModel() {
    private val mainRepo: MainRepo = MainRepo()
    val getCocktailList: MutableLiveData<Drinks?>
        get() = mainRepo.getCocktailLiveData

}