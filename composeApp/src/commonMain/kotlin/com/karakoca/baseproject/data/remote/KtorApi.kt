package com.karakoca.baseproject.data.remote


import KmpBaseProject.composeApp.BuildConfig
import com.karakoca.baseproject.data.local.Constants.BASE_URL
import com.karakoca.baseproject.platform.chuckerProvider
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
internal abstract class KtorApi {

    private val chuckerInterceptor = chuckerProvider().getOkhttpEngine()
    private val httpClient =
        if (chuckerInterceptor != null) HttpClient(chuckerInterceptor) else HttpClient()

    protected val client = httpClient.config {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
                prettyPrint = true
                isLenient = true
                explicitNulls = false
            })

        }

        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
    }

    fun HttpRequestBuilder.pathUrl(path: String) {
        contentType(ContentType.Application.Json)
        url {
            takeFrom(BASE_URL)
            path("3", path)
            parameter("api_key", BuildConfig.API_KEY)
        }
    }
}