package com.karakoca.baseproject.data.model.error


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TmdbError(
    @SerialName("status_code")
    val statusCode: Int?,
    @SerialName("status_message")
    val statusMessage: String?,
    @SerialName("success")
    val success: Boolean?
)