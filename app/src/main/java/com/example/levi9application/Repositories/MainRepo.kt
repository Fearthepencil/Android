package com.example.levi9application.Repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.levi9application.Common.Common
import com.example.levi9application.Model.Drinks
import com.example.levi9application.Network.APIService
import retrofit2.Call
import retrofit2.Response

class MainRepo {
    private val apiService: APIService

    companion object {
        private const val TAG = "MainRepo"
    }

    init {
        apiService = Common.getAPIService
    }

    val getCocktailLiveData: MutableLiveData<Drinks?>
        get() {
            val data: MutableLiveData<Drinks?> =
                MutableLiveData<Drinks?>()
            apiService.getCocktailList()
                .enqueue(object : retrofit2.Callback<Drinks> {
                    override fun onResponse(
                        call: Call<Drinks>,
                        response: Response<Drinks>
                    ) {
                        Log.e(TAG, "onResponse: " + response.code() + response.body())
                        if (response.isSuccessful) {
                            data.value = response.body()
                        } else {
                            data.value = null
                        }
                    }

                    override fun onFailure(call: Call<Drinks>, t: Throwable) {
                        Log.e(TAG, "onResponse: " + t.message)
                        data.value = null
                    }
                })
            return data
        }
}