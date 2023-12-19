package com.chrrissoft.downloadmanager.entities

import android.app.DownloadManager

sealed interface DownloadReason {
    sealed interface Error {
        object UNKNOWN : Error
        object FILE_ERROR : Error
        object UNHANDLED_HTTP_CODE : Error
        object HTTP_DATA_ERROR : Error
        object TOO_MANY_REDIRECTS : Error
        object INSUFFICIENT_SPACE : Error
        object DEVICE_NOT_FOUND : Error
        object CANNOT_RESUME : Error
        object FILE_ALREADY_EXISTS : Error
        object BLOCKED : Error

        companion object {
            fun Error(reason: Int): Error {
                return when (reason) {
                    DownloadManager.ERROR_UNKNOWN -> UNKNOWN
                    DownloadManager.ERROR_FILE_ERROR -> FILE_ERROR
                    DownloadManager.ERROR_UNHANDLED_HTTP_CODE -> UNHANDLED_HTTP_CODE
                    DownloadManager.ERROR_HTTP_DATA_ERROR -> HTTP_DATA_ERROR
                    DownloadManager.ERROR_TOO_MANY_REDIRECTS -> TOO_MANY_REDIRECTS
                    DownloadManager.ERROR_INSUFFICIENT_SPACE -> INSUFFICIENT_SPACE
                    DownloadManager.ERROR_DEVICE_NOT_FOUND -> DEVICE_NOT_FOUND
                    DownloadManager.ERROR_CANNOT_RESUME -> CANNOT_RESUME
                    DownloadManager.ERROR_FILE_ALREADY_EXISTS -> FILE_ALREADY_EXISTS
                    1010 -> BLOCKED
                    else -> UNKNOWN
                }
            }
        }
    }

    sealed interface Paused {
        object WAITING_TO_RETRY : Paused
        object WAITING_FOR_NETWORK : Paused
        object QUEUED_FOR_WIFI : Paused
        object UNKNOWN : Paused

        companion object {
            fun Paused(reason: Int): Paused {
                return when (reason) {
                    DownloadManager.PAUSED_WAITING_TO_RETRY -> WAITING_TO_RETRY
                    DownloadManager.PAUSED_WAITING_FOR_NETWORK -> WAITING_FOR_NETWORK
                    DownloadManager.PAUSED_QUEUED_FOR_WIFI -> QUEUED_FOR_WIFI
                    DownloadManager.PAUSED_UNKNOWN -> UNKNOWN
                    else -> UNKNOWN
                }
            }
        }
    }

}
