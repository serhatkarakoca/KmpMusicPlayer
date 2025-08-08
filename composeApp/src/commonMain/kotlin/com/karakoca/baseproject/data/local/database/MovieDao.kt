package com.karakoca.baseproject.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.karakoca.baseproject.data.model.local.FavMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM favMovie")
    fun getAllFavoriteMovies(): Flow<List<FavMovie>>

    @Insert
    suspend fun upsert(movie: FavMovie)

    @Delete
    suspend fun delete(favMovie: FavMovie)

    @Query("SELECT * FROM favMovie WHERE movieId = :id")
    suspend fun getFavoriteMovie(id: Int): FavMovie?
}