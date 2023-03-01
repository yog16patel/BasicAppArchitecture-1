package com.example.basicapparchitecture.data.repository

import com.example.basicapparchitecture.data.api.MovieApi
import com.example.basicapparchitecture.data.api.movieApiResponseMapper.MovieApiResponseMapper
import com.example.simpleappforinterview.domain.entities.MovieInfo
import com.example.basicapparchitecture.domain.repositories.MovieRepository
import com.example.basicapparchitecture.domain.common.Result
class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val movieApiResponseMapper: MovieApiResponseMapper
): MovieRepository {

    override suspend fun getMovies(): Result<List<MovieInfo>> {
        val response = movieApi.getAllMovies()
        return if(response.isSuccessful){
            val body = response.body()
            Result.Success(movieApiResponseMapper.apply(body))
        } else{
            Result.Error(response.message())
        }
    }
}