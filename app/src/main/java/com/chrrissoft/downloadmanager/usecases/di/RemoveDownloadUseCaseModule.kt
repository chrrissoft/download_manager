package com.chrrissoft.downloadmanager.usecases.di

import com.chrrissoft.downloadmanager.usecases.classes.RemoveDownloadUseCaseImpl
import com.chrrissoft.downloadmanager.usecases.interfaces.RemoveDownloadUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RemoveDownloadUseCaseModule {
    @Binds
    abstract fun binds(impl: RemoveDownloadUseCaseImpl) : RemoveDownloadUseCase
}
