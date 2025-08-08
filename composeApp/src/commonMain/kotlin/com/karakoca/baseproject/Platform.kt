package com.karakoca.baseproject

interface Platform {
    val name: String
    val deviceId: String
}

expect fun getPlatform(): Platform