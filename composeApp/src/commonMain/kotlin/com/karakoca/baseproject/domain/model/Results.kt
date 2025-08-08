package com.karakoca.baseproject.domain.model


data class Results(
    val id: Int?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val voteAverage: Double?,
    var isFavorite: Boolean = false
)
