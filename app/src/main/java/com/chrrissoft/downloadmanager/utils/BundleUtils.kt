package com.chrrissoft.downloadmanager.utils

import android.net.Uri
import android.os.Parcelable
import androidx.core.os.BundleCompat.getParcelableArray
import com.chrrissoft.downloadmanager.Constants
import com.chrrissoft.downloadmanager.Constants.Extras.EXTRA_LINK
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import android.os.Bundle as BundleOs

object BundleUtils {
    @Suppress("FunctionName")
    fun Bundle(res: Map<Uri, Boolean>): BundleOs {
        return BundleOs().apply {
            putString(Constants.Extras.EXTRA_LINK_RESULT, Json.encodeToString(res))
        }
    }

    val BundleOs.links: List<Uri> get() = getParcels(EXTRA_LINK)


    private inline fun<reified T: Parcelable> BundleOs.getParcels(key: String): List<T> {
        return getParcelableArray((this), key, T::class.java)
            ?.toList()?.map { it as T } ?: emptyList()
    }
}
