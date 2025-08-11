package com.karakoca.baseproject.domain.usecase

import com.karakoca.baseproject.data.model.Resource
import com.karakoca.baseproject.data.model.music.SearchResponseDTO
import com.karakoca.baseproject.domain.repository.MusicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchUseCase : KoinComponent {
    private val repository: MusicRepository by inject()

    suspend operator fun invoke(query: String): Flow<Resource<SearchResponseDTO>> {
        return repository.search(query).map {
            when (it) {
                is Resource.Success -> Resource.Success(it.result)
                is Resource.Error -> Resource.Error(it.throwable)
                Resource.Loading -> Resource.Loading
            }
        }
    }
}

