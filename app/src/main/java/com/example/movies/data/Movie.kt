package com.example.movies.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("actors")
    val actors: String,
    @SerialName("posterUrl")
    val posterUrl: String,
    @SerialName("year")
    val year: String,
    @SerialName("plot")
    val plot: String
)