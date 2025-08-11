package com.karakoca.baseproject.presentation.home

import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.karakoca.baseproject.base.BaseViewModel
import com.karakoca.baseproject.base.Event
import com.karakoca.baseproject.base.State
import com.karakoca.baseproject.data.local.AppPreferencesRepository
import com.karakoca.baseproject.data.local.database.MusicDao
import com.karakoca.baseproject.data.model.music.Playlists
import com.karakoca.baseproject.data.model.music.Video
import com.karakoca.baseproject.domain.usecase.SearchUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val searchUseCase: SearchUseCase,
    private val appPreferencesRepository: AppPreferencesRepository,
    private val dao: MusicDao
) :
    BaseViewModel<HomeState, HomeEvent>() {


    override fun setInitialState(): HomeState = HomeState()

    override fun handleEvent(event: HomeEvent) {
        viewModelScope.launch {
            when (event) {
                is HomeEvent.Search -> search(event.query)
            }
        }
    }

    private suspend fun search(query: String) {
        setLoadingState(true)
        searchUseCase.invoke(query).sendRequest(onComplete = {
            updateState(
                getCurrentState().copy(
                    videos = it.videos ?: emptyList(),
                    playlists = it.playlists ?: emptyList()
                )
            )
            setLoadingState(false)
        }, onError = {
            Logger.e("onError", it, "onError")
            setLoadingState(false)
        })
    }

    fun setLoadingState(value: Boolean) {
        updateState(getCurrentState().copy(isLoading = value))
    }
}

data class HomeState(
    val isLoading: Boolean = false,
    val videos: List<Video> = emptyList(),
    val playlists: List<Playlists> = emptyList(),
) : State

sealed interface HomeEvent : Event {
    data class Search(val query: String) : HomeEvent
}