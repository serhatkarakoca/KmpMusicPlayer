package com.karakoca.baseproject.platform

import io.ktor.client.engine.HttpClientEngine

interface ChuckerProvider {
    fun getOkhttpEngine(): HttpClientEngine?
}

expect fun chuckerProvider(): ChuckerProvider