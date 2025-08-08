package com.karakoca.baseproject

import platform.Foundation.NSUUID
import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val deviceId: String
        get() = UIDevice.currentDevice.identifierForVendor?.UUIDString ?: NSUUID().UUIDString
}

actual fun getPlatform(): Platform = IOSPlatform()