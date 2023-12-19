package com.chrrissoft.downloadmanager.utils

import android.app.DownloadManager
import com.chrrissoft.downloadmanager.entities.DownloadRequest
import com.chrrissoft.downloadmanager.entities.ResState
import com.chrrissoft.downloadmanager.entities.Result
import com.chrrissoft.downloadmanager.utils.DownloadRequestUtils.toManagerRequest

object DownloadManagerUtils {
    fun DownloadManager.enqueue(data: DownloadRequest): Result<Long> {
        return try {
            Result.Success(enqueue(data.toManagerRequest()))
        } catch (e: Throwable) {
            Result.Error(e)
        }
    }
}
