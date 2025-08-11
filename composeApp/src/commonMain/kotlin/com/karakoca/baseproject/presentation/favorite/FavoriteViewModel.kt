package com.karakoca.baseproject.presentation.favorite

import androidx.lifecycle.viewModelScope
import com.karakoca.baseproject.base.BaseViewModel
import com.karakoca.baseproject.base.Event
import com.karakoca.baseproject.base.State
import com.karakoca.baseproject.data.local.database.MusicDao
import kotlinx.coroutines.launch

class FavoriteViewModel(private val dao: MusicDao) : BaseViewModel<FavoriteState, FavoriteEvent>() {
    override fun setInitialState(): FavoriteState {
        return FavoriteState()
    }

    override fun handleEvent(event: FavoriteEvent) {
        viewModelScope.launch {
            when (event) {
                FavoriteEvent.GetFavorites -> {}
            }
        }
    }

    init {
        handleEvent(FavoriteEvent.GetFavorites)
    }


}

data class FavoriteState(
    val isLoading: Boolean = false,
) : State

sealed interface FavoriteEvent : Event {
    data object GetFavorites : FavoriteEvent
}