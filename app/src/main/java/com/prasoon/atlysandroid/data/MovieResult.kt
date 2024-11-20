package com.prasoon.atlysandroid.data


data class MovieResultList(
    val results: List<MovieResult>
)

data class MovieResult(
    val id: String,
    val overview: String,
    val posterPath: String
)