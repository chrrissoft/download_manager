package com.chrrissoft.downloadmanager.usecases.di

import com.chrrissoft.downloadmanager.usecases.classes.GetDownloadItemsUseCaseImpl
import com.chrrissoft.downloadmanager.usecases.interfaces.GetDownloadItemsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class GetDownloadItemsUseCaseModule {
    @Binds
    abstract fun binds(impl: GetDownloadItemsUseCaseImpl) : GetDownloadItemsUseCase
}
