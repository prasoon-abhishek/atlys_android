package com.prasoon.atlysandroid.vm

import androidx.lifecycle.ViewModel

class MovieViewModel : ViewModel() {

    fun getMovieDetail(){
        ApiClient.getMovieById()
    }
}