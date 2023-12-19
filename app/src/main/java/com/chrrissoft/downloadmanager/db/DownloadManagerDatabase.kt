package com.chrrissoft.downloadmanager.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DownloadRequest::class], version = 1)
abstract class DownloadManagerDatabase : RoomDatabase() {
    abstract val downloadRequestDao: DownloadRequestDao
}
