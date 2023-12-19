package com.chrrissoft.downloadmanager.usecases.di

import com.chrrissoft.downloadmanager.usecases.classes.EnqueueDownloadUseCaseImpl
import com.chrrissoft.downloadmanager.usecases.interfaces.EnqueueDownloadUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class EnqueueDownloadUseCaseModule {
    @Binds
    abstract fun binds(impl: EnqueueDownloadUseCaseImpl) : EnqueueDownloadUseCase
}
