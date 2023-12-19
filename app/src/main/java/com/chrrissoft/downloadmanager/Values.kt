package com.chrrissoft.downloadmanager

import android.app.DownloadManager.Request.*
import com.chrrissoft.downloadmanager.utils.Util.ui

object Values {
    val visibilities = buildMap {
        put("VISIBILITY_VISIBLE_NOTIFY_COMPLETED".ui, VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        put("VISIBILITY_VISIBLE".ui, VISIBILITY_VISIBLE)
        put("VISIBILITY_HIDDEN".ui, VISIBILITY_HIDDEN)
    }.toList()
}
