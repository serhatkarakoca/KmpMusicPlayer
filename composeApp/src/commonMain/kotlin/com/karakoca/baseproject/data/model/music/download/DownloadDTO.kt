package com.karakoca.baseproject.data.model.music.download


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DownloadDTO(
    @SerialName("downloadUrl")
    val downloadUrl: String?,
    @SerialName("duration")
    val duration: String?,
    @SerialName("thumbnail")
    val thumbnail: String?,
    @SerialName("title")
    val title: String?
)