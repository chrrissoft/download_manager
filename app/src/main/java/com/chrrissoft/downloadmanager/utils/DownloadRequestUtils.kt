package com.chrrissoft.downloadmanager.utils

import android.app.DownloadManager
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.N
import com.chrrissoft.downloadmanager.entities.DownloadRequest
import com.chrrissoft.downloadmanager.entities.Location
import com.chrrissoft.downloadmanager.entities.ResState
import com.chrrissoft.downloadmanager.entities.Result
import com.chrrissoft.downloadmanager.db.DownloadRequest as DownloadRequestDb

object DownloadRequestUtils {
    fun DownloadRequest.toManagerRequest(): DownloadManager.Request {
        return DownloadManager.Request(Uri.parse(url)).apply {
            setTitle(title)
            setDescription(description)
            setAllowedOverMetered(allowedOverMetered)
            setAllowedOverRoaming(allowedOverRoaming)
            setAllowedNetworkTypes(networkType.original)
            setNotificationVisibility(visibility.second)
            if (location is Location.Custom) throw IllegalArgumentException("Custom locations not implemented yet ☹️")
            setDestinationInExternalPublicDir(location.value, name)
            if (SDK_INT >= N) setRequiresCharging(requiresCharging)
            if (SDK_INT >= N) setRequiresDeviceIdle(requiresDeviceIdle)
        }
    }

    fun DownloadRequest.toDb(id: Long): DownloadRequestDb {
        return DownloadRequestDb(
            id = id,
            description = description,
            networkType = networkType,
            allowedOverMetered = allowedOverMetered,
            allowedOverRoaming = allowedOverRoaming,
            title = title,
            location = location.value,
            visibility = visibility.second,
            requiresCharging = requiresCharging,
            requiresDeviceIdle = requiresDeviceIdle,
            url = url,
        )
    }

    fun ResState<Map<DownloadRequest, Result<*>>>.countText(
        operation: String,
        loading: String = "Loading",
        error: (Throwable?) -> String = { it?.message ?: "Unknown error" },
    ) : String {
        return when (this) {
            is ResState.Error -> error(throwable)
            ResState.Loading -> loading
            is ResState.Success -> {
                val successCount = data.count { it.value is Result.Success }
                "$successCount request has ben $operation of ${data.size}"
            }
        }
    }

}
