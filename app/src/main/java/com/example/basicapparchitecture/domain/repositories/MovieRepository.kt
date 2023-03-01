package com.example.basicapparchitecture.domain.repositories

import com.example.simpleappforinterview.domain.entities.MovieInfo
import com.example.basicapparchitecture.domain.common.Result

interface MovieRepository {

    suspend fun getMovies(): Result<List<MovieInfo>>
}