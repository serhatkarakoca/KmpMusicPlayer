package com.karakoca.baseproject.domain.repository

import com.karakoca.baseproject.data.model.Resource
import com.karakoca.baseproject.data.model.discover.DiscoverMoviesDTO
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(page: Int): Flow<Resource<DiscoverMoviesDTO>>
}