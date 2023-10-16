package com.example.youtube.core.network

import com.example.youtube.BuildConfig
import com.example.youtube.core.base.BaseDataSource
import com.example.youtube.data.model.PlayListModel
import com.example.youtube.presentation.utils.Constants

class RemoteDataSource(private val apiService: ApiService):BaseDataSource()  {
    suspend fun getPlaylists( pageToken:String): Result<PlayListModel> {
            return getResult {
                apiService.getPlayLists(
                    part = Constants.PART,
                    channelId = Constants.CHANNEL_ID,
                    apiKey = BuildConfig.API_KEY,
                    maxResult = 10,
                    pageToken =pageToken
                )
            }
        }

        suspend fun getPlaylistItems(playlistid: String): Result<PlayListModel>{
            return getResult {
                apiService.getPlaylistItem(
                    part = Constants.PART,
                    apiKey = BuildConfig.API_KEY,
                    playlistId = playlistid,
                    maxResult = 22,
                )
            }
        }

    suspend fun getVideo(videoId: String): Result<PlayListModel>{
        return getResult {
            apiService.getVideo(
                part = Constants.PART,
                apiKey = BuildConfig.API_KEY,
                videoId = videoId
            )
        }
    }
    }