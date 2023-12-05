package com.example.levi9application.common

import com.example.levi9application.network.APIService
import com.example.levi9application.repositories.CocktailRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Common {
    private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

    @Provides
    @Singleton
    fun provideAPI(): APIService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }

    @Provides
    @Singleton
    fun cocktailRepo(apiService: APIService) : CocktailRepo{
        return CocktailRepo(apiService)
    }

}