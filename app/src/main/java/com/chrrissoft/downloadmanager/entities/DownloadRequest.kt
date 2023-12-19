package com.chrrissoft.downloadmanager.entities

import com.chrrissoft.downloadmanager.Values
import kotlin.random.Random

data class DownloadRequest(
    val id: Long = 0,
    val description: String = "",
    val networkType: NetworkType = NetworkType.BOTH,
    val allowedOverMetered: Boolean = true,
    val allowedOverRoaming: Boolean = true,
    val title: String = "",
    val location: Location = Location.directories[2],
    val visibility: Pair<String, Int> = Values.visibilities.first(),
    val requiresCharging: Boolean = false,
    val requiresDeviceIdle: Boolean = false,
    val url: String = "",
    val name: String = Random.nextInt().toString(),
)
