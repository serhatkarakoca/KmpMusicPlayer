package com.karakoca.baseproject.data.remote


import androidx.compose.ui.text.intl.Locale
import com.karakoca.baseproject.data.model.Resource
import com.karakoca.baseproject.data.model.discover.DiscoverMoviesDTO
import com.karakoca.baseproject.data.model.error.TmdbError
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

    suspend fun getMovies(page: Int) = client.safeRequest<DiscoverMoviesDTO> {
        method = Get
        parameter("page", page)
        parameter("language", Locale.current.language)
        pathUrl("discover/movie")
    }

    private suspend inline fun <reified T> HttpClient.safeRequest(
        block: HttpRequestBuilder.() -> Unit,
    ): Resource<T> =
        try {
            val response = request { block() }
            if (response.status == HttpStatusCode.OK)
                Resource.Success(response.body())
            else {
                val error = response.body() as? TmdbError
                val request = response.request.url
                Resource.Error(Throwable(message = error?.statusMessage + " : " + request.encodedPath + request.encodedQuery))
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