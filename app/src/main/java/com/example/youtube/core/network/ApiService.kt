package com.example.youtube.core.network

import com.bumptech.glide.load.engine.Resource
import com.example.youtube.data.model.PlayListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("playlists")
   suspend fun getPlayLists(
        @Query("part") part: String,
        @Query("key") apiKey: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResult: Int,
        @Query("pageToken") pageToken:String

    ): Response<PlayListModel>

    @GET("playlistItems")
    suspend fun getPlaylistItem(
        @Query("part") part: String,
        @Query("key") apiKey: String,
        @Query("playlistId") playlistId: String,
        @Query("maxResults") maxResult: Int
    ): Response<PlayListModel>

    @GET("videos")
    suspend fun getVideo(
        @Query("part") part: String,
        @Query("key") apiKey: String,
        @Query("id") videoId: String
    ): Response<PlayListModel>
}