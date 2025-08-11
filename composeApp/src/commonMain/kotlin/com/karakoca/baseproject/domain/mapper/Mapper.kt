package com.karakoca.baseproject.domain.mapper


interface Mapper<F, T> {
    fun fromTo(from: F): T
}

fun <T, V> convertGeneric(input: T, converter: (T) -> V): V {
    return converter(input)
}

