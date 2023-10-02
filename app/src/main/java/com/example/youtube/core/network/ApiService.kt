package com.example.youtube.core.network

import com.example.youtube.core.utils.Resource
import com.example.youtube.data.model.PlayListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("playlists")
    fun getPlayLists(
        @Query("part")part:String,
        @Query("key")apiKey:String,
        @Query("channelId")chenalId:String,
        @Query("maxResults")maxResult:Int,
    ):Call<PlayListModel>
}