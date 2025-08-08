package com.karakoca.baseproject.data.model.discover


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiscoverMoviesDTO(
    @SerialName("page")
    val page: Int?,
    @SerialName("results")
    val results: List<ResultDTO?>?,
    @SerialName("total_pages")
    val totalPages: Int?,
    @SerialName("total_results")
    val totalResults: Int?
)