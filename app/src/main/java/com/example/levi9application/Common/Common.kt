package com.example.levi9application.Common

import com.example.levi9application.Network.APIService
import com.example.levi9application.Network.Retrofit

object Common {
    private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"
    val getAPIService: APIService
        get() = Retrofit.getRetrofitClient(BASE_URL).create(APIService::class.java)
}