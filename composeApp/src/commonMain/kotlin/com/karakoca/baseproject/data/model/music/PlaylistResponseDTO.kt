package com.karakoca.baseproject.data.model.music

import kotlinx.serialization.SerialName

data class PlaylistResponseDTO(
    @SerialName("playlistId")
    val playlistId: String?,
    @SerialName("totalVideos")
    val totalVideos: Int?,
    @SerialName("videos")
    val videos: List<Video>?
)
