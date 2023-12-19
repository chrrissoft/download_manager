package com.chrrissoft.downloadmanager.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material.icons.rounded.Downloading
import androidx.compose.ui.graphics.vector.ImageVector
import com.chrrissoft.downloadmanager.entities.DownloadItem
import com.chrrissoft.downloadmanager.entities.DownloadQuery
import com.chrrissoft.downloadmanager.entities.DownloadRequest
import com.chrrissoft.downloadmanager.entities.ResState
import com.chrrissoft.downloadmanager.entities.ResState.Success
import com.chrrissoft.downloadmanager.ui.entities.SnackbarData

data class MainScreenState(
    val page: Page = Page.REQUEST,
    val snackbar: SnackbarData = SnackbarData(),
    val requestPage: RequestPageState = RequestPageState(),
    val listingPage: ListingPageState = ListingPageState(),
) {
    enum class Page(val icon: ImageVector) {
        REQUEST(Icons.Rounded.Build), LISTING(Icons.Rounded.Downloading),
        ;

        companion object {
            val pages = listOf(REQUEST, LISTING)
        }
    }

    data class RequestPageState(val request: DownloadRequest = DownloadRequest())

    data class ListingPageState(
        val ids: ResState<Set<Long>> = ResState.Loading,
        val query: DownloadQuery = DownloadQuery(),
        val list: ResState<List<ResState<DownloadItem>>> = ResState.Loading,
    )
}
