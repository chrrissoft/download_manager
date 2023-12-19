package com.chrrissoft.downloadmanager.di

import androidx.room.Room
import com.chrrissoft.downloadmanager.DownloadManagerApp
import com.chrrissoft.downloadmanager.Constants
import com.chrrissoft.downloadmanager.db.DownloadManagerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provide(app: DownloadManagerApp) : DownloadManagerDatabase {
        return Room.databaseBuilder(
            context = app,
            name = Constants.DATABASE_NAME,
            klass = DownloadManagerDatabase::class.java,
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
