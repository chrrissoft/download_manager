package com.chrrissoft.downloadmanager.entities

import android.app.DownloadManager


data class DownloadQuery(
    val ids: Set<Long> = emptySet(),
    val states: Set<DownloadState> = emptySet(),
) {
    companion object {
        fun DownloadQuery.toManagerQuery(): DownloadManager.Query {
            return DownloadManager.Query().apply {
                setFilterById(*ids.toLongArray())
            }
        }
    }
}
