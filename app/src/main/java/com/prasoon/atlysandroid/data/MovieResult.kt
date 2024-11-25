package com.prasoon.atlysandroid.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


data class MovieResultList(
    val results: List<MovieResult>
)

@Serializable
data class MovieResult(
    val id: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    val title: String
)