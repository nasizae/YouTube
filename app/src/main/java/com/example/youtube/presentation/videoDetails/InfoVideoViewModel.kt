package com.example.youtube.presentation.videoDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube.core.base.BaseViewModel
import com.example.youtube.data.model.PlayListModel
import com.slottica.reviewfueatures.youtube57_3.domain.repository.Repository

class InfoVideoViewModel(private val repository: Repository): BaseViewModel() {

    private var _video = MutableLiveData<PlayListModel>()
    val video: LiveData<PlayListModel> get() = _video

    fun getVideo(videoId: String) = doOperation(
        operation = { repository.getVideo(videoId) },
        success = { _video.postValue(it) }
    )
}