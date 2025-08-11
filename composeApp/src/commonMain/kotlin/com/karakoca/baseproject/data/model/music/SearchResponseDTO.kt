package com.karakoca.baseproject.data.model.music


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponseDTO(
    @SerialName("playlists")
    val playlists: List<Playlists>?,
    @SerialName("videos")
    val videos: List<Video>?
)