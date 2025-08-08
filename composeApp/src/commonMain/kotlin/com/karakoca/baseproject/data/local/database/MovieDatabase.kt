package com.karakoca.baseproject.data.local.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.karakoca.baseproject.data.model.local.FavMovie

@Database(
    entities = [FavMovie::class],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class MovieDatabase() : RoomDatabase() {
    abstract fun getDao(): MovieDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<MovieDatabase>
