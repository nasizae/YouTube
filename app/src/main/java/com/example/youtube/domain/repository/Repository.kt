package com.slottica.reviewfueatures.youtube57_3.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube.BuildConfig
import com.example.youtube.core.network.ApiService
import com.example.youtube.core.utils.Resource
import com.example.youtube.data.model.PlayListModel
import com.example.youtube.presentation.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val apiService: ApiService) {

    fun getPlaylists(): LiveData<Resource<PlayListModel>> {
        val resourceData = MutableLiveData<Resource<PlayListModel>>()
        apiService.getPlayLists(
            part = Constants.PART,
            chenalId = Constants.CHANNEL_ID,
            apiKey = BuildConfig.API_KEY,
            maxResult = 11,
        ).enqueue(
            object : Callback<PlayListModel> {
                override fun onResponse(
                    call: Call<PlayListModel>,
                    response: Response<PlayListModel>,
                ) {
                    if (response.isSuccessful) {
                        resourceData.value = Resource.success(response.body())
                    } else {
                        resourceData.value = Resource.error(
                            msg = response.message().toString(),
                            data = null,
                            code = 429
                        )
                    }
                }

                override fun onFailure(call: Call<PlayListModel>, t: Throwable) {
                    resourceData.value = Resource.error(
                        msg = t.message.toString(),
                        data = null,
                        code = 429
                    )
                }
            }
        )
        return resourceData
    }
}