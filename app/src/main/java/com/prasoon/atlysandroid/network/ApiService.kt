package com.prasoon.atlysandroid.network

import com.prasoon.atlysandroid.data.MovieResultList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/movie?include_adult=false&language=en-US&page=1")
    suspend fun getMovieList(
        @Query("query") id: String,
        @Query("api_key") apiKey: String?
    ): Response<MovieResultList>

    @GET("trending/movie/day?language=en-US")
    suspend fun getMovieList(@Query("api_key") apiKey: String?): Response<MovieResultList>
}