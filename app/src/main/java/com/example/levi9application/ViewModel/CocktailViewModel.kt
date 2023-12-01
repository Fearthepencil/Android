package com.example.levi9application.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.levi9application.Model.Drinks
import com.example.levi9application.Repositories.MainRepo

class CocktailViewModel: ViewModel() {
    private val mainRepo: MainRepo
    val getCocktailList: MutableLiveData<Drinks?>
        get() = mainRepo.getCocktailLiveData

    init{
        mainRepo = MainRepo()
    }

}