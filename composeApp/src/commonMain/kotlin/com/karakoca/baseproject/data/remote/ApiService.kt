package com.karakoca.baseproject.data.remote


import androidx.compose.ui.text.intl.Locale
import com.karakoca.baseproject.data.model.Resource
import com.karakoca.baseproject.data.model.music.PlaylistResponseDTO
import com.karakoca.baseproject.data.model.music.SearchResponseDTO
import com.karakoca.baseproject.data.model.music.download.DownloadDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.request
import io.ktor.http.HttpMethod.Companion.Delete
import io.ktor.http.HttpMethod.Companion.Get
import io.ktor.http.HttpMethod.Companion.Patch
import io.ktor.http.HttpMethod.Companion.Post
import io.ktor.http.HttpStatusCode
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException

internal class ApiService : KtorApi() {

    suspend fun search(query: String) = client.safeRequest<SearchResponseDTO> {
        method = Get
        parameter("q", query)
        pathUrl("search")
    }

    suspend fun getPlaylistDetails(playlistId: String) = client.safeRequest<PlaylistResponseDTO> {
        method = Get
        parameter("id", playlistId)
        pathUrl("playlist")
    }

    suspend fun getMusicDetails(url: String) = client.safeRequest<DownloadDTO> {
        method = Get
        parameter("url", url)
        pathUrl("download")
    }

    private suspend inline fun <reified T> HttpClient.safeRequest(
        block: HttpRequestBuilder.() -> Unit,
    ): Resource<T> =
        try {
            val response = request { block() }
            if (response.status == HttpStatusCode.OK)
                Resource.Success(response.body())
            else {
                val request = response.request.url
                Resource.Error(Throwable(message = "ERROR" + " : " + request.encodedPath + request.encodedQuery))
            }
        } catch (e: ClientRequestException) {
            Resource.Error(e)
        } catch (e: ServerResponseException) {
            Resource.Error(e)
        } catch (e: IOException) {
            Resource.Error(e)
        } catch (e: SerializationException) {
            Resource.Error(e)
        } catch (e: Throwable) {
            Resource.Error(e)
        }
}