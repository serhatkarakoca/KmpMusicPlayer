package com.karakoca.baseproject.platform

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.karakoca.baseproject.data.local.database.MusicDatabase
import kotlinx.coroutines.Dispatchers

internal actual fun getRoomDatabase(): MusicDatabase {
    val dbFile = applicationContext.getDatabasePath("favMovie.db")
    return Room.databaseBuilder<MusicDatabase>(
        context = applicationContext,
        name = dbFile.absolutePath
    )
        .fallbackToDestructiveMigrationOnDowngrade(false)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}

