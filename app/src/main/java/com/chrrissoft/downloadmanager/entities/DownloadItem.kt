package com.chrrissoft.downloadmanager.entities


data class DownloadItem(
    val id: Long = 2342341234,
    val state: DownloadState = DownloadState.Pending,
    val mimeType: String?,
    val description: String?,
    val title: String?,
    private val totalKb: Double?,
    private val soFarKb: Double?,
    val uri: String?,
) {
    val percentage = StorageUnit.KB(getPercentage(soFarKb, totalKb) ?: 0.0)

    val soFar = StorageUnit.KB(soFarKb ?: 0.0)
    val total = StorageUnit.KB(totalKb ?: 0.0)

    fun getPercentage(soFar: Double?, total: Double?): Double? {
        return soFar
    }
}
