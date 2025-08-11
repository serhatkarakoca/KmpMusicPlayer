package com.karakoca.baseproject.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("music")
data class MusicEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long
)
