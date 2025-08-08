package com.karakoca.baseproject

import android.os.Build
import java.util.UUID

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val deviceId: String
        get() = UUID.randomUUID().toString()
}

actual fun getPlatform(): Platform = AndroidPlatform()