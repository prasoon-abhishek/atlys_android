package com.prasoon.atlysandroid.network

import android.graphics.Movie
import com.google.gson.JsonObject
import com.prasoon.atlysandroid.data.MovieResultList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/{id}")
    fun getMovieById(@Path("id") id: String, @Query("api_key") apiKey: String?): Call<JsonObject?>?

    @GET("trending/movie/day?language=en-US")
    suspend fun getMovieList(@Query("api_key") apiKey: String?): Response<MovieResultList>

    @GET("/{posterPath}")
    fun getMoviePosterImage(@Path("posterPath") posterPath: String): Call<JsonObject?>?
}