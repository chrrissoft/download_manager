package com.chrrissoft.downloadmanager.di

import android.os.Looper
import com.chrrissoft.downloadmanager.DownloadManagerApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppLooperModule {
    @Provides
    fun provide(app: DownloadManagerApp): Looper = app.mainLooper
}
