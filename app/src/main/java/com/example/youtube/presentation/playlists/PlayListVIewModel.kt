package com.example.youtube.presentation.playlists

import androidx.lifecycle.LiveData
import com.example.youtube.core.base.BaseViewModel
import com.example.youtube.core.utils.Resource
import com.example.youtube.data.model.PlayListModel
import com.slottica.reviewfueatures.youtube57_3.domain.repository.Repository

class PlayListVIewModel(private val repository: Repository) : BaseViewModel() {
    fun getPlaylists(): LiveData<Resource<PlayListModel>> {
        return repository.getPlaylists()
    }
}