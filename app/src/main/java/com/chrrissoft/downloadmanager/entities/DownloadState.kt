package com.chrrissoft.downloadmanager.entities

import android.app.DownloadManager

sealed interface DownloadState {
    object Pending : DownloadState
    object Unknown : DownloadState
    object Running : DownloadState
    object Successful : DownloadState
    data class Failed(val reason: DownloadReason.Error) : DownloadState
    data class Paused(val reason: DownloadReason.Paused) : DownloadState

    companion object {
        fun DownloadState(status: Int, reason: Int): DownloadState {
            return when (status) {
                DownloadManager.STATUS_PENDING -> Pending
                DownloadManager.STATUS_RUNNING -> Running
                DownloadManager.STATUS_SUCCESSFUL -> Successful
                DownloadManager.STATUS_FAILED -> Failed(DownloadReason.Error.Error(reason))
                DownloadManager.STATUS_PAUSED -> Paused(DownloadReason.Paused.Paused(reason))
                else -> Unknown
            }
        }

        val States = buildList {
            add(Pending)
            add(Unknown)
            add(Running)
            add(Successful)
            add(Failed(DownloadReason.Error.UNKNOWN))
            add(Paused(DownloadReason.Paused.UNKNOWN))
        }
    }
}
