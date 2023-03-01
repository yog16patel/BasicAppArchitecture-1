package com.example.basicapparchitecture.data.api

import com.example.basicapparchitecture.data.api.models.Movie
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {
    @GET("movielist.json")
    suspend fun getAllMovies() : Response<List<Movie>>
}