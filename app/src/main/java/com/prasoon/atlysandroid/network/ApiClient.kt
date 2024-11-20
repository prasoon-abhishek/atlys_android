package com.prasoon.atlysandroid.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiClient {
    object ApiClient {
        private const val API_KEY = "e4ca6605cd6999cdb956f2fc3695cecf"
        private val apiService: ApiService by lazy {
            RetrofitClient.getRetrofitInstance().create(ApiService::class.java)
        }

        fun getMovieById() {
            apiService.getMovieById("27181", API_KEY)?.enqueue(object : Callback<JsonObject?> {
                override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
//                TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<JsonObject?>, t: Throwable) {
//                TODO("Not yet implemented")
                }

            })
        }
    }