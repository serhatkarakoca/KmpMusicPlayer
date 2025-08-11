package com.karakoca.baseproject.data.model.music


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Video(
    @SerialName("author")
    val author: String?,
    @SerialName("duration")
    val duration: String?,
    @SerialName("thumbnail")
    val thumbnail: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("url")
    val url: String?,
    @SerialName("videoId")
    val videoId: String?,
    @SerialName("views")
    val views: Int?
)