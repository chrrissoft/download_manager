package com.chrrissoft.downloadmanager.di

import android.os.Messenger
import com.chrrissoft.downloadmanager.app.DownloadFileServiceHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DownloadFileServiceModule {
    @Provides
    fun provide(handler: DownloadFileServiceHandler): Messenger = Messenger(handler)
}
