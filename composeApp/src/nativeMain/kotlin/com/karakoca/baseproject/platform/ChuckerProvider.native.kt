package com.karakoca.baseproject.platform

import io.ktor.client.engine.HttpClientEngine

class ChuckerProviderIos : ChuckerProvider {
    override fun getOkhttpEngine(): HttpClientEngine? {
        return null
    }

}

actual fun chuckerProvider(): ChuckerProvider = ChuckerProviderIos()