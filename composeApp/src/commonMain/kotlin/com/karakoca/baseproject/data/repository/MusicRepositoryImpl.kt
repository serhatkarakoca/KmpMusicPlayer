package com.karakoca.baseproject.data.repository

import com.karakoca.baseproject.data.model.Resource
import com.karakoca.baseproject.data.model.music.PlaylistResponseDTO
import com.karakoca.baseproject.data.model.music.SearchResponseDTO
import com.karakoca.baseproject.data.model.music.download.DownloadDTO
import com.karakoca.baseproject.data.remote.RemoteDataSource
import com.karakoca.baseproject.domain.repository.MusicRepository
import kotlinx.coroutines.flow.Flow


internal class MusicRepositoryImpl(private val remoteDataSource: RemoteDataSource) :
    MusicRepository {

    override suspend fun search(query: String): Flow<Resource<SearchResponseDTO>> {
        return remoteDataSource.search(query)
    }

    override suspend fun getPlaylistDetails(playlistId: String): Flow<Resource<PlaylistResponseDTO>> {
        return remoteDataSource.getPlaylistDetails(playlistId)
    }

    override suspend fun getMusicDetails(url: String): Flow<Resource<DownloadDTO>> {
        return remoteDataSource.getMusicDetails(url)
    }

}

