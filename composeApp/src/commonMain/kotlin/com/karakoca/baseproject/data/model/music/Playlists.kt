package com.karakoca.baseproject.data.model.music


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Playlists(
    @SerialName("author")
    val author: String?,
    @SerialName("playlistId")
    val playlistId: String?,
    @SerialName("thumbnail")
    val thumbnail: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("url")
    val url: String?
)