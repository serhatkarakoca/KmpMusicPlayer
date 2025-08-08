package com.karakoca.baseproject.presentation.favorite

import androidx.lifecycle.viewModelScope
import com.karakoca.baseproject.base.BaseViewModel
import com.karakoca.baseproject.base.Event
import com.karakoca.baseproject.base.State
import com.karakoca.baseproject.data.local.database.MovieDao
import com.karakoca.baseproject.data.model.local.FavMovie
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoriteViewModel(private val dao: MovieDao) : BaseViewModel<FavoriteState, FavoriteEvent>() {
    override fun setInitialState(): FavoriteState {
        return FavoriteState()
    }

    override fun handleEvent(event: FavoriteEvent) {
        viewModelScope.launch {
            when (event) {
                is FavoriteEvent.GetFavorites -> getFavorites()
                is FavoriteEvent.DeleteFavorite -> deleteFavorite(event.item)
            }
        }
    }

    init {
        handleEvent(FavoriteEvent.GetFavorites)
    }

    private suspend fun getFavorites() {
        dao.getAllFavoriteMovies().collectLatest {
            updateState(getCurrentState().copy(favorites = it))
        }
    }

    private suspend fun deleteFavorite(item: FavMovie) {
        dao.delete(item)
    }
}

data class FavoriteState(
    val isLoading: Boolean = false,
    val favorites: List<FavMovie> = emptyList()
) : State

sealed interface FavoriteEvent : Event {
    data object GetFavorites : FavoriteEvent
    data class DeleteFavorite(val item: FavMovie) : FavoriteEvent
}