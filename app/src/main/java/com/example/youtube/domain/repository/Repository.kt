package com.slottica.reviewfueatures.youtube57_3.domain.repository

import com.bumptech.glide.load.engine.Resource
import com.example.youtube.core.network.ApiService
import com.example.youtube.core.network.RemoteDataSource
import com.example.youtube.data.model.PlayListModel

class Repository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getPlaylists(): Result<PlayListModel> {
        return remoteDataSource.getPlaylists()
    }

    suspend fun getPlaylistItems(playlistId: String): Result<PlayListModel> {
        return remoteDataSource.getPlaylistItems(playlistId)
    }
}