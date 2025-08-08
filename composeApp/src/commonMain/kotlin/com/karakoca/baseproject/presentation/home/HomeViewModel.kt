package com.karakoca.baseproject.presentation.home

import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.karakoca.baseproject.base.BaseViewModel
import com.karakoca.baseproject.base.Event
import com.karakoca.baseproject.base.State
import com.karakoca.baseproject.data.local.AppPreferencesRepository
import com.karakoca.baseproject.data.local.database.MovieDao
import com.karakoca.baseproject.data.model.local.FavMovie
import com.karakoca.baseproject.domain.model.Results
import com.karakoca.baseproject.domain.usecase.GetMoviesUseCase
import com.karakoca.baseproject.getPlatform
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val appPreferencesRepository: AppPreferencesRepository,
    private val dao: MovieDao
) :
    BaseViewModel<HomeState, HomeEvent>() {

    private var page = 1
    override fun setInitialState(): HomeState = HomeState()

    override fun handleEvent(event: HomeEvent) {
        viewModelScope.launch {
            when (event) {
                is HomeEvent.GetMovies -> getMovies()
            }
        }
    }

    init {
        setUserId()
        handleEvent(HomeEvent.GetMovies)
    }

    private fun setUserId() {
        viewModelScope.launch {
            appPreferencesRepository.getUserId().ifEmpty {
                appPreferencesRepository.setUserId(getPlatform().deviceId)
            }
        }
    }

    private suspend fun getMovies() {
        if (getCurrentState().isLoading || getCurrentState().loadFinished)
            return

        getMoviesUseCase.invoke(page).sendRequest(onComplete = {
            val movieList = it.results?.mapNotNull { item -> item } ?: emptyList()
            val finalMovieList = if (page == 0) movieList else getCurrentState().movies + movieList
            updateState(
                getCurrentState().copy(
                    movies = finalMovieList,
                    loadFinished = movieList.size < 20
                )
            )
            page++
        }, onError = {
            Logger.e("onError", it, "onError")
        })
    }

    fun setLoadingState(value: Boolean) {
        updateState(getCurrentState().copy(isLoading = value))
    }

    fun upsert(mov: Results) {
        viewModelScope.launch {
            if (dao.getFavoriteMovie(mov.id ?: 0) == null) {
                val favMovie = FavMovie(
                    movieId = mov.id ?: 0,
                    title = mov.title ?: "",
                    image = mov.posterPath.toString()
                )
                dao.upsert(favMovie)
                mov.isFavorite = true
            }
        }
    }

    fun deleteFav(mov: Results) {
        viewModelScope.launch {
            val favMovie = dao.getFavoriteMovie(mov.id ?: 0)
            favMovie?.let { fav ->
                dao.delete(fav)
            }
        }
    }
}

data class HomeState(
    val isLoading: Boolean = false,
    val movies: List<Results> = emptyList(),
    val loadFinished: Boolean = false
) : State

sealed interface HomeEvent : Event {
    data object GetMovies : HomeEvent
}