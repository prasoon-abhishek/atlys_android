package com.prasoon.atlysandroid.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prasoon.atlysandroid.network.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
) : ViewModel() {

    @Inject
    lateinit var repository: MoviesRepository

    fun getMovieDetail() {
        viewModelScope.launch {
            repository.getMovieList().collect {
                Log.d("movieResult", it.data?.results?.get(0)?.id ?: "no result")
            }
        }
    }
}