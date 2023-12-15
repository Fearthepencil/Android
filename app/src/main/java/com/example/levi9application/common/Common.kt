package com.example.levi9application.common

import android.content.Context
import com.example.levi9application.database.CocktailDAO
import com.example.levi9application.database.CocktailDatabase
import com.example.levi9application.network.APIService
import com.example.levi9application.repositories.CocktailRepo
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
    private val retrofit = provideRetrofit()

    @Provides
    fun provideContext(
        @ApplicationContext context: Context
    ): Context {
        return context
    }
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }
    @Provides
    @Singleton
    fun provideAPI(): APIService {
        return retrofit.create(APIService::class.java)
    }

    @Provides
    @Singleton
    fun provideCocktailRepo(apiService: APIService): CocktailRepo {
        return CocktailRepo(apiService)
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): CocktailDatabase {
        return CocktailDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideDao(database: CocktailDatabase): CocktailDAO {
        return database.getDao()
    }

}