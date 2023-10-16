package com.example.youtube.presentation.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.example.youtube.core.base.BaseViewModel
import com.example.youtube.data.model.PlayListModel
import com.slottica.reviewfueatures.youtube57_3.domain.repository.Repository

class PlayListVIewModel(private val repository: Repository) : BaseViewModel() {
//    private val _playlists = MutableLiveData<PlayListModel>()
//    val playlists: LiveData<PlayListModel> get() = _playlists

    fun getPagingPlaylists(): LiveData<PagingData<PlayListModel.Item>> {
        return repository.getPlaylists()

    }


//    fun getPlaylists() {
//        doOperation(
//            operation = { repository.getPlaylists()},
//            success = { _playlists.postValue(it) })
//    }
}