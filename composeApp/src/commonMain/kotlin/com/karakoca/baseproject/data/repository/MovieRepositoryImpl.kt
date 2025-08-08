package com.karakoca.baseproject.data.repository

import com.karakoca.baseproject.data.model.Resource
import com.karakoca.baseproject.data.model.discover.DiscoverMoviesDTO
import com.karakoca.baseproject.data.remote.RemoteDataSource
import com.karakoca.baseproject.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow


internal class MovieRepositoryImpl(private val remoteDataSource: RemoteDataSource) :
    MovieRepository {
    override suspend fun getMovies(page: Int): Flow<Resource<DiscoverMoviesDTO>> {
        return remoteDataSource.getMovies(page)
    }

}

