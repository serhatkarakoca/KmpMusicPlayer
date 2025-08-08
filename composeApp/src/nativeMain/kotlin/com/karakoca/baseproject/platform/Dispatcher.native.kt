package com.karakoca.baseproject.platform

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class IOSDispatcher : Dispatcher {
    override val io: CoroutineDispatcher
        get() = Dispatchers.Main

}

internal actual fun provideDispatcher(): Dispatcher = IOSDispatcher()