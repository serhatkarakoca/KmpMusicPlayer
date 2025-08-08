package com.karakoca.baseproject.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavMovie(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val movieId: Int,
    val title: String,
    val image: String
)
