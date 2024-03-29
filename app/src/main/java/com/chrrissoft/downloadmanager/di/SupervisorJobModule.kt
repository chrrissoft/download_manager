package com.chrrissoft.downloadmanager.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SupervisorJobModule {
    @Provides
    @Singleton
    fun provide(): Job = SupervisorJob()
}
