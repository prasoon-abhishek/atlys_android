package com.prasoon.atlysandroid.network

import android.util.Log
import com.google.gson.JsonObject
import com.prasoon.atlysandroid.data.MovieResultList
import com.prasoon.atlysandroid.data.Resource
import com.prasoon.atlysandroid.di.Qualifiers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val apiServiceMovie: ApiService,
    private val apiServiceImage: ApiService,
) {

    fun getMovieById() {
        apiServiceMovie.getMovieById("27181", RetrofitClient.API_KEY)
            ?.enqueue(object : Callback<JsonObject?> {
                override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
//                TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<JsonObject?>, t: Throwable) {
//                TODO("Not yet implemented")
                }

            })
    }

    fun getMovieList(): Flow<Resource<MovieResultList>> {
        return flow {
            emit(ApiResponse.safeApiCall { apiServiceMovie.getMovieList(RetrofitClient.API_KEY) })
        }.flowOn(Dispatchers.Default)
    }

    fun getMoviePosterImage(posterPath: String) {
        apiServiceImage.getMoviePosterImage(posterPath)?.enqueue(object : Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
//                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<JsonObject?>, t: Throwable) {
//                TODO("Not yet implemented")
            }

        })
    }
}