package com.example.youtube.core.di

import com.example.youtube.presentation.playListItem.PlayListItemViewModel
import com.example.youtube.presentation.playlists.PlayListVIewModel
import com.example.youtube.presentation.videoDetails.InfoVideoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PlayListVIewModel(get()) }
    viewModel { PlayListItemViewModel(get()) }
    viewModel { InfoVideoViewModel(get()) }
}