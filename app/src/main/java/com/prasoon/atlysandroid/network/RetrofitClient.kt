package com.prasoon.atlysandroid.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    const val API_KEY = "e4ca6605cd6999cdb956f2fc3695cecf"
    private const val BASE_URL_MOVIE = "https://api.themoviedb.org/3/"

    fun getRetrofitInstance(): Retrofit {
        val client = OkHttpClient()
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val clientBuilder: OkHttpClient.Builder =
            client.newBuilder().addInterceptor(interceptor)

        return Retrofit.Builder().baseUrl(BASE_URL_MOVIE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.build())
            .build()
    }
}
