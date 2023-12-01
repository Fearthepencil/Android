package com.example.levi9application.Network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Retrofit {
    private var retrofit:Retrofit? = null
    fun getRetrofitClient(baseURl: String): Retrofit{

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .readTimeout((60*2).toLong(), TimeUnit.SECONDS)
            .connectTimeout((60*2).toLong(), TimeUnit.SECONDS)
            .writeTimeout((60*2).toLong(), TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

        if(retrofit==null){
            retrofit = Retrofit.Builder()
                .baseUrl(baseURl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }

        return retrofit!!
    }
}