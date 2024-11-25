package com.prasoon.atlysandroid.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.prasoon.atlysandroid.MoviesNavGraph
import com.prasoon.atlysandroid.data.MovieResult
import com.prasoon.atlysandroid.data.Resource
import com.prasoon.atlysandroid.network.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
) : ViewModel() {
    @Inject
    lateinit var repository: MoviesRepository

    lateinit var navController: NavHostController
        private set

    private val _movieList = MutableStateFlow<List<MovieResult>>(emptyList())
    val movieList: StateFlow<List<MovieResult>> = _movieList

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error

    private val _movieDetail = MutableStateFlow<MovieResult?>(null)
    val movieDetail: StateFlow<MovieResult?> = _movieDetail

    private fun getMovieDetail() {
        viewModelScope.launch {
            repository.getMovieList().collect { movieList ->
                when (movieList) {
                    is Resource.Error -> {
                        navController.navigate(MoviesNavGraph.Error(movieList.message))
                    }

                    is Resource.Success -> {
                        _movieList.value = movieList.data?.results ?: emptyList()
                        navController.navigate(MoviesNavGraph.MovieList) {
                            popUpTo(MoviesNavGraph.Loading) {
                                inclusive = true
                            }
                        }
                    }

                    is Resource.Loading -> {
                        navController.navigate(MoviesNavGraph.Loading)
                    }
                }
            }
        }
    }

    fun onActivityCreated(navController: NavHostController) {
        this.navController = navController
        getMovieDetail()
    }

    fun onMovieClicked(movie: MovieResult) {
        _movieDetail.value = movie
        navController.navigate(MoviesNavGraph.MovieDetail)
    }

    fun onMovieSearch(text: String) {
        viewModelScope.launch {
            repository.getMovieList(text).collect { movieList ->
                when (movieList) {
                    is Resource.Error -> {
                        navController.navigate(MoviesNavGraph.Error(movieList.message))
                    }

                    is Resource.Success -> {
                        _movieList.value =
                            movieList.data?.results?.filter { it.posterPath.isNullOrEmpty().not() }
                                ?: emptyList()
                    }

                    is Resource.Loading -> {
                        navController.navigate(MoviesNavGraph.Loading)
                    }
                }
            }
        }
    }
}