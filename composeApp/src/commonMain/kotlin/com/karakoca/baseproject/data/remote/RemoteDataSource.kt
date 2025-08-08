package com.karakoca.baseproject.data.remote

import com.karakoca.baseproject.data.model.Resource
import com.karakoca.baseproject.platform.Dispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart


internal class RemoteDataSource(private val api: ApiService, private val dispatcher: Dispatcher) {
    suspend fun getMovies(page: Int) = flow {
        emit(api.getMovies(page))
    }.handleResourceFlow()


    private fun <T> Flow<Resource<T>>.handleResourceFlow(): Flow<Resource<T>> =
        onStart { emit(Resource.Loading) }
            .map {
                when (it) {
                    is Resource.Success -> {
                        Resource.Success(it.result)
                    }

                    is Resource.Error -> Resource.Error(it.throwable)
                    else -> it
                }
            }.catch { throwable -> emit(Resource.Error(throwable)) }
            .flowOn(dispatcher.io)
}