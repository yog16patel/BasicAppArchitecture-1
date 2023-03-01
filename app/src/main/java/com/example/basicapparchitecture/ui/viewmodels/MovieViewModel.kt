package com.example.basicapparchitecture.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicapparchitecture.data.api.MovieApi
import com.example.basicapparchitecture.data.api.NetworkModule
import com.example.basicapparchitecture.data.api.movieApiResponseMapper.MovieApiResponseMapper
import com.example.basicapparchitecture.domain.common.Result
import com.example.basicapparchitecture.domain.repositories.MovieRepository
import com.example.basicapparchitecture.data.repository.MovieRepositoryImpl
import com.example.simpleappforinterview.domain.entities.MovieInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class MovieViewModelState(
    val movieInfoList : List<MovieInfo> = emptyList()
)

class MovieViewModel : ViewModel() {
    private val movieApi: MovieApi = NetworkModule.createMovieApi()
    private val movieRepository: MovieRepository = MovieRepositoryImpl(movieApi, MovieApiResponseMapper())
    private val _movieListFlow = MutableStateFlow(MovieViewModelState())
    val movieListFlow : StateFlow<MovieViewModelState>
        get() = _movieListFlow

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            val res = movieRepository.getMovies()
            _movieListFlow.update {
                when (res) {
                    is Result.Success ->  it.copy(movieInfoList = res.data)
                    else -> it
                }
            }
        }
    }

}