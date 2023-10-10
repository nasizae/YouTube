package com.example.youtube.core.di

import com.example.youtube.core.network.RemoteDataSource
import org.koin.dsl.module

val remoteDataSource = module {
    single { RemoteDataSource(get()) }
}