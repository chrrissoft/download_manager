package com.chrrissoft.downloadmanager.di

import android.app.DownloadManager
import androidx.core.content.getSystemService
import com.chrrissoft.downloadmanager.DownloadManagerApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DownloadManagerModule {
    @Provides
    fun provide(app: DownloadManagerApp): DownloadManager = app.getSystemService()!!
}
