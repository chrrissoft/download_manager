package com.chrrissoft.downloadmanager.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chrrissoft.downloadmanager.entities.Location
import com.chrrissoft.downloadmanager.entities.NetworkType
import java.util.UUID
import com.chrrissoft.downloadmanager.entities.DownloadRequest as DownloadRequestCommon

@Entity(tableName = "download_requests")
data class DownloadRequest(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "networkType") val networkType: NetworkType,
    @ColumnInfo(name = "allowed_over_metered") val allowedOverMetered: Boolean,
    @ColumnInfo(name = "allowed_over_roaming") val allowedOverRoaming: Boolean,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "location") val location: String,
    @ColumnInfo(name = "visibility") val visibility: Int,
    @ColumnInfo(name = "requires_charging") val requiresCharging: Boolean,
    @ColumnInfo(name = "requires_device_idle") val requiresDeviceIdle: Boolean,
    @ColumnInfo(name = "url") val url: String,
) {
    companion object {
        fun DownloadRequest.toCommon(): DownloadRequestCommon {
            return DownloadRequestCommon(id = id)
        }
    }
}
