package com.chrrissoft.downloadmanager.entities

import android.app.DownloadManager
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.ui.graphics.vector.ImageVector

enum class NetworkType(val icon: ImageVector, val original: Int) {
    MOBILE(Icons.Rounded.Favorite, DownloadManager.Request.NETWORK_MOBILE),
    WIFI(Icons.Rounded.Favorite, DownloadManager.Request.NETWORK_WIFI),
    BOTH(Icons.Rounded.Favorite, DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI),
    ;

    companion object {
        val types = listOf(BOTH, WIFI, MOBILE)
    }
}
