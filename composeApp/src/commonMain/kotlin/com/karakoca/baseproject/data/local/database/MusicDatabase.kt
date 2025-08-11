package com.karakoca.baseproject.data.local.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.karakoca.baseproject.data.model.local.MusicEntity

@Database(
    entities = [MusicEntity::class],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class MusicDatabase() : RoomDatabase() {
    abstract fun getDao(): MusicDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<MusicDatabase>
