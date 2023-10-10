package com.example.youtube.core.di

import com.slottica.reviewfueatures.youtube57_3.domain.repository.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single { Repository(get()) }
}