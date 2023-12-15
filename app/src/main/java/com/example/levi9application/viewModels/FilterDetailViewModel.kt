package com.example.levi9application.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levi9application.models.Category
import com.example.levi9application.models.Resource
import com.example.levi9application.repositories.CategoryRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterDetailViewModel
@Inject constructor(private val categoryRepo: CategoryRepo) :
    ViewModel() {

    private var job: Job? = null
    private val _response = MutableLiveData<Resource<List<Category>>>()
    val getCategoryList: LiveData<Resource<List<Category>>>
        get() = _response

    private val debounce = 500L

    private val handler = CoroutineExceptionHandler { _, e ->
        _response.value = e.message?.let { Resource.Error(it) }
    }


    fun getCategories(queries: Map<String,String>) {
        job?.cancel()
        job = viewModelScope.launch(handler) {
            _response.value = Resource.Loading()
            if (queries.isNotEmpty()) {
                delay(debounce)
            }
            val response = categoryRepo.getCategoryList(queries)
            if (response.isSuccessful) {
                val drinks = response.body()?.categories ?: emptyList()
                _response.value = Resource.Success(drinks)
            } else {
                _response.value = Resource.Error(response.message())
            }
        }

    }


}
