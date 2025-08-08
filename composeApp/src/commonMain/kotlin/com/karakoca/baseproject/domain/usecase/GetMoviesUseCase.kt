package com.karakoca.baseproject.domain.usecase

import com.karakoca.baseproject.data.model.Resource
import com.karakoca.baseproject.domain.mapper.toDiscoverMovies
import com.karakoca.baseproject.domain.model.DiscoverMovies
import com.karakoca.baseproject.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetMoviesUseCase : KoinComponent {
    private val repository: MovieRepository by inject()

    suspend operator fun invoke(page: Int): Flow<Resource<DiscoverMovies>> {
        return repository.getMovies(page).map {
            when (it) {
                is Resource.Success -> Resource.Success(it.result.toDiscoverMovies())
                is Resource.Error -> Resource.Error(it.throwable)
                Resource.Loading -> Resource.Loading
            }
        }
    }
}

