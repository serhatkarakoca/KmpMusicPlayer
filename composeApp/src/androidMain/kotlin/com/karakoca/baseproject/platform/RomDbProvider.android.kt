package com.karakoca.baseproject.platform

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.karakoca.baseproject.data.local.database.MovieDatabase
import kotlinx.coroutines.Dispatchers

internal actual fun getRoomDatabase(): MovieDatabase {
    val dbFile = applicationContext.getDatabasePath("favMovie.db")
    return Room.databaseBuilder<MovieDatabase>(
        context = applicationContext,
        name = dbFile.absolutePath
    )
        .fallbackToDestructiveMigrationOnDowngrade(false)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}

