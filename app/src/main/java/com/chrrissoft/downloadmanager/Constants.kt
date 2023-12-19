package com.chrrissoft.downloadmanager

object Constants {
    const val DATABASE_NAME = "download_manager_database"


    object Actions {
        const val ACTION_DOWNLOAD_SUCCESS =
            "com.chrrissoft.downloadmanager.action.ACTION_DOWNLOAD_SUCCESS"
        const val ACTION_DOWNLOAD_LOADING =
            "com.chrrissoft.downloadmanager.action.ACTION_DOWNLOAD_LOADING"
        const val ACTION_DOWNLOAD_ERROR =
            "com.chrrissoft.downloadmanager.action.ACTION_DOWNLOAD_ERROR"
    }

    object Extras {
        const val EXTRA_LINK = "com.chrrissoft.downloadmanager.extra.EXTRA_LINK"
        const val EXTRA_LINK_RESULT = "com.chrrissoft.downloadmanager.extra.EXTRA_LINK_RESULT"
    }


    object ClientMessages {
        const val MSG_REGISTER_CLIENT = 1

        const val MSG_UNREGISTER_CLIENT = 2

        const val MSG_ENQUEUE_DOWNLOAD = 3
    }

    object ServiceMessages {
        const val MSG_ENQUEUE_DOWNLOAD_SUCCESS = 4

        const val MSG_ENQUEUE_DOWNLOAD_ERROR = 5

        const val MSG_ENQUEUE_DOWNLOAD_LOADING = 6

        const val MSG_DOWNLOAD_CONNECTION_CONNECTED = 7

        const val MSG_DOWNLOAD_CONNECTION_DISCONNECTED = 8
    }
}
