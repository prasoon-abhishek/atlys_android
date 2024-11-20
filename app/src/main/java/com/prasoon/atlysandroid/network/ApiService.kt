package com.prasoon.atlysandroid.network

import android.graphics.Movie
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/{id}")
    fun getMovieById(@Path("id") id: String, @Query("api_key") apiKey: String?): Call<JsonObject?>?

    @GET("trending/movie/day?language=en-US")
    fun getMovieList(@Query("api_key") apiKey: String?): Call<JsonObject?>?
}