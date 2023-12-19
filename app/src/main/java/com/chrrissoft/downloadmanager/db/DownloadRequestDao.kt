package com.chrrissoft.downloadmanager.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DownloadRequestDao {
    @Query("SELECT * FROM download_requests")
    fun get(): Flow<List<DownloadRequest>>

    @Query("SELECT * FROM download_requests WHERE :id = id")
    fun get(id: String): Flow<DownloadRequest>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg users: DownloadRequest)

    @Delete
    suspend fun delete(vararg users: DownloadRequest)
}
