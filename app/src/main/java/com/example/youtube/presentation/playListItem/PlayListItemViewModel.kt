package com.example.youtube.presentation.playListItem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube.core.base.BaseViewModel
import com.example.youtube.data.model.PlayListModel
import com.slottica.reviewfueatures.youtube57_3.domain.repository.Repository

class PlayListItemViewModel(private val repository: Repository):BaseViewModel() {
    private val _playlists = MutableLiveData<PlayListModel>()
    val playlistsItem: LiveData<PlayListModel> get() = _playlists
    fun getPlaylists(id:String) =doOperation(
            operation = { repository.getPlaylistItems(id)},
            success = { _playlists.postValue(it) })
}