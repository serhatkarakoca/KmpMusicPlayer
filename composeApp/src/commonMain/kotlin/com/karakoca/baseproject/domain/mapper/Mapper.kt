package com.karakoca.baseproject.domain.mapper

import com.karakoca.baseproject.data.model.discover.DiscoverMoviesDTO
import com.karakoca.baseproject.domain.model.DiscoverMovies
import com.karakoca.baseproject.domain.model.Results

interface Mapper<F, T> {
    fun fromTo(from: F): T
}

fun <T, V> convertGeneric(input: T, converter: (T) -> V): V {
    return converter(input)
}

fun DiscoverMoviesDTO.toDiscoverMovies(): DiscoverMovies {
    return DiscoverMovies(this.page, this.results?.mapNotNull { result ->
        result?.let {
            Results(
                it.id,
                it.originalLanguage,
                it.originalTitle,
                it.overview,
                "https://image.tmdb.org/t/p/w500/" + it.posterPath,
                it.releaseDate,
                it.title,
                it.voteAverage
            )
        }
    }
    )
}