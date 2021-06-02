package com.example.movies.http

import com.example.movies.data.MoviesResponse
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.serialization.decodeFromString

class MoviesApi(private val client: HttpClient) {
    private val json = kotlinx.serialization.json.Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
    }

    suspend fun getMovies(): MoviesResponse {
        val responseString: String =
            client.get("https://raw.githubusercontent.com/yossiavramov/MoviesList/master/MoviesList")
        return json.decodeFromString(responseString)
    }
}