package com.example.youtube.core.di

import com.example.youtube.core.network.provideApiService
import com.example.youtube.core.network.provideInterceptor
import com.example.youtube.core.network.provideOkHttpClient
import com.example.youtube.core.network.provideRetrofitClient
import org.koin.dsl.module

val networkMode = module {
    single { provideInterceptor() }
    single { provideOkHttpClient(get()) }
    factory { provideRetrofitClient(get()) }
    single { provideApiService(get()) }
}