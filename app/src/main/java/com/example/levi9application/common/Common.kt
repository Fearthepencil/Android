package com.example.levi9application.common

import android.content.Context
import com.example.levi9application.model.CocktailDatabase
import com.example.levi9application.network.APIService
import com.example.levi9application.repositories.CocktailRepo
import com.example.levi9application.view.CocktailDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Common {
    private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

    @Provides
    fun provideContext(
        @ApplicationContext context: Context
    ): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideAPI(): APIService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }

    @Provides
    @Singleton
    fun cocktailRepo(apiService: APIService): CocktailRepo {
        return CocktailRepo(apiService)
    }

    @Provides
    @Singleton
    fun getDatabase(context: Context): CocktailDatabase {
        return CocktailDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun getDao(database: CocktailDatabase): CocktailDAO {
        return database.getDao()
    }

}