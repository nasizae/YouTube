package com.slottica.reviewfueatures.youtube57_3.domain.repository

import PagingSource
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.youtube.core.network.RemoteDataSource
import com.example.youtube.data.model.PlayListModel

class Repository(private val remoteDataSource: RemoteDataSource) {

//    suspend fun getPlaylists(): Result<PlayListModel> {
//        return remoteDataSource.getPlaylists()
//    }

    fun getPlaylists(): LiveData<PagingData<PlayListModel.Item>> {
        val pagingData= Pager(
            config = PagingConfig(
                initialLoadSize = 20,
                pageSize = Integer.MAX_VALUE,
                enablePlaceholders = false,
                prefetchDistance = 5
            ), pagingSourceFactory = {
                PagingSource(remoteDataSource=remoteDataSource,"key1","")
            }
        )
        return pagingData.liveData
        }
    suspend fun getPlaylistItems(playlistId: String): Result<PlayListModel> {
        return remoteDataSource.getPlaylistItems(playlistId)
    }

    suspend fun getVideo(videoId:String): Result<PlayListModel> {
        return remoteDataSource.getVideo(videoId)
    }
}