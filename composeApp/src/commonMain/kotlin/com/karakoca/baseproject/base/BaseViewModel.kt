package com.karakoca.baseproject.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karakoca.baseproject.data.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE : State, EVENT : Event> : ViewModel() {
    private val initialState: STATE by lazy { setInitialState() }

    abstract fun setInitialState(): STATE
    abstract fun handleEvent(event: EVENT)

    private val _state: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state.asStateFlow()


    private val _event: MutableSharedFlow<EVENT> = MutableSharedFlow()
    val event: SharedFlow<EVENT> = _event.asSharedFlow()

    private val _loading = MutableStateFlow(false)
    val loadingState = _loading.asStateFlow()

    init {
        subscribeEvent()
    }

    private fun subscribeEvent() {
        viewModelScope.launch {
            _event.collect { handleEvent(it) }
        }
    }

    fun updateState(state: STATE) {
        viewModelScope.launch { _state.update { state } }
    }

    fun setEvent(event: EVENT) {
        viewModelScope.launch { _event.emit(event) }
    }

    fun getCurrentState() = state.value

    fun setLoading(value: Boolean) {
        viewModelScope.launch { _loading.emit(value) }
    }

    inline fun <T> Flow<Resource<T>>.sendRequest(
        showProgress: Boolean = true,
        crossinline onComplete: (T) -> Unit,
        crossinline onError: (Throwable) -> Unit
    ) {
        onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    if (showProgress)
                        setLoading(true)
                }

                is Resource.Success -> {
                    setLoading(false)
                    onComplete(response.result)
                }

                is Resource.Error -> {
                    setLoading(false)
                    onError(response.throwable)
                }
            }
        }.launchIn(viewModelScope)
    }

    inline fun <T> Flow<Resource<T>>.sendRequest(
        showProgress: Boolean = true,
        crossinline onComplete: (T) -> Unit
    ) {
        onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    if (showProgress)
                        setLoading(true)
                }

                is Resource.Success -> {
                    setLoading(false)
                    onComplete(response.result)
                }

                is Resource.Error -> {
                    setLoading(false)
                }
            }
        }.launchIn(viewModelScope)
    }
}

interface State
interface Event
