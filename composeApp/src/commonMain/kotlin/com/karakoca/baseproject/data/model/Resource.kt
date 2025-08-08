package com.karakoca.baseproject.data.model

sealed class Resource<out T> {
    data object Loading : Resource<Nothing>()
    data class Success<T>(val result: T) : Resource<T>()
    data class Error(val throwable: Throwable) : Resource<Nothing>()
}