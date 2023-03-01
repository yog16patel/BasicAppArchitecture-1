package com.example.basicapparchitecture.data.api.movieApiResponseMapper

import com.example.basicapparchitecture.data.api.models.Movie
import com.example.simpleappforinterview.domain.entities.MovieInfo
import java.util.function.Function

class MovieApiResponseMapper: Function<List<Movie>?,List<MovieInfo>> {
    override fun apply(movieList: List<Movie>?): List<MovieInfo> {
        return movieList?.map {
            MovieInfo(
                it.category,
                it.desc,
                it.imageUrl,
                it.name
            )
        } ?: emptyList()
    }
}