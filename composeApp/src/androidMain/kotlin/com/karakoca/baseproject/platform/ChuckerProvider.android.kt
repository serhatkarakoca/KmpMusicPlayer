package com.karakoca.baseproject.platform

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

class ChuckerProviderAndroid : ChuckerProvider {
    override fun getOkhttpEngine(): HttpClientEngine? {
        // Setting the interceptor
        val chuckerInterceptor = ChuckerInterceptor.Builder(applicationContext)
            .collector(ChuckerCollector(applicationContext))
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()

        return OkHttp.create {
            addInterceptor(chuckerInterceptor)
        }
    }

}

actual fun chuckerProvider(): ChuckerProvider = ChuckerProviderAndroid()