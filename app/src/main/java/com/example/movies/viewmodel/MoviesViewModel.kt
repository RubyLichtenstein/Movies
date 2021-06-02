package com.example.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.Movie
import com.example.movies.http.MoviesApi
import com.example.movies.http.ktorHttpClient
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {
    private val moviesApi = MoviesApi(ktorHttpClient)

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    val navigateToMovieDetail: MutableSharedFlow<Movie?> = MutableStateFlow(null)

    init {
        viewModelScope.launch {
            val response = moviesApi.getMovies()
            _uiState.value = UiState.Success(response.movies)
        }
    }
}

sealed class UiState {
    data class Success(val movies: List<Movie>) : UiState()
    data class Error(val exception: Throwable) : UiState()
    object Loading : UiState()
}
