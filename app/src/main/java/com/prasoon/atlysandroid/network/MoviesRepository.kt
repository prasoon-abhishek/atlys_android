package com.prasoon.atlysandroid.network

import com.prasoon.atlysandroid.data.MovieResultList
import com.prasoon.atlysandroid.data.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val apiServiceMovie: ApiService,
) {

    fun getMovieList(searchString: String): Flow<Resource<MovieResultList>> {
        return flow {
            emit(ApiResponse.safeApiCall {
                apiServiceMovie.getMovieList(searchString, RetrofitClient.API_KEY)
            })
        }.flowOn(Dispatchers.Default)
    }

    fun getMovieList(): Flow<Resource<MovieResultList>> {
        return flow {
            emit(ApiResponse.safeApiCall { apiServiceMovie.getMovieList(RetrofitClient.API_KEY) })
        }.flowOn(Dispatchers.Default)
    }
}