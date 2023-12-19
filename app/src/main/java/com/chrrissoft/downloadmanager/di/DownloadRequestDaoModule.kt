package com.chrrissoft.downloadmanager.di

import com.chrrissoft.downloadmanager.db.DownloadManagerDatabase
import com.chrrissoft.downloadmanager.db.DownloadRequestDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DownloadRequestDaoModule {
    @Provides
    fun provide(database: DownloadManagerDatabase) : DownloadRequestDao = database.downloadRequestDao
}
