package com.chrrissoft.downloadmanager.di

import android.content.Context
import com.chrrissoft.downloadmanager.DownloadManagerApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provide(@ApplicationContext ctx: Context): DownloadManagerApp = ctx as DownloadManagerApp
}
