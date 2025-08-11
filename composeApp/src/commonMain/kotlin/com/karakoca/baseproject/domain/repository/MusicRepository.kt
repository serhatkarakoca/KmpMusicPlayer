package com.karakoca.baseproject.domain.repository

import com.karakoca.baseproject.data.model.Resource
import com.karakoca.baseproject.data.model.music.PlaylistResponseDTO
import com.karakoca.baseproject.data.model.music.SearchResponseDTO
import com.karakoca.baseproject.data.model.music.download.DownloadDTO
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    suspend fun search(query: String): Flow<Resource<SearchResponseDTO>>
    suspend fun getPlaylistDetails(playlistId: String): Flow<Resource<PlaylistResponseDTO>>
    suspend fun getMusicDetails(url: String): Flow<Resource<DownloadDTO>>
}