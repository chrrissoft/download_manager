package com.chrrissoft.downloadmanager.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutineExceptionHandlerModule {
    @Provides
    @Singleton
    fun provide(): CoroutineExceptionHandler = CoroutineExceptionHandler { _, _ -> }
}
