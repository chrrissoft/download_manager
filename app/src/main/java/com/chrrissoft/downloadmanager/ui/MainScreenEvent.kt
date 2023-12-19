package com.chrrissoft.downloadmanager.ui

import com.chrrissoft.downloadmanager.entities.DownloadItem
import com.chrrissoft.downloadmanager.entities.DownloadQuery
import com.chrrissoft.downloadmanager.entities.DownloadRequest
import com.chrrissoft.downloadmanager.ui.MainViewModel.DownloadScreenEventHandler.ListingPageEventHandler
import com.chrrissoft.downloadmanager.ui.MainViewModel.DownloadScreenEventHandler.RequestPageEventHandler

sealed interface MainScreenEvent {
    fun resolve(handler: MainViewModel.DownloadScreenEventHandler) {
        when (this) {
            is RequestPageEvent -> resolve(handler.request)
            is ListingPageEvent -> resolve(handler.listing)
            is OnChangePage -> handler.onEvent(event = this)
        }
    }

    sealed interface RequestPageEvent : MainScreenEvent {
        fun resolve(handler: RequestPageEventHandler) {
            when (this) {
                is OnChangeRequest -> handler.onEvent(event = this)
                is OnLaunch -> handler.onEvent(event = this)
            }
        }
        data class OnChangeRequest(val data: DownloadRequest)
            : RequestPageEvent

        data class OnLaunch(val data: List<DownloadRequest>) :
            RequestPageEvent
    }

    sealed interface ListingPageEvent : MainScreenEvent {
        fun resolve(handler: ListingPageEventHandler) {
            when (this) {
                is OnChangeQuery -> handler.onEvent(event = this)
                is OnRemove -> handler.onEvent(event = this)
                is OnRunQuery -> handler.onEvent(event = this)
            }
        }

        data class OnRunQuery(val data: DownloadQuery) : ListingPageEvent
        data class OnChangeQuery(val data: DownloadQuery) : ListingPageEvent
        data class OnRemove(val data: List<DownloadItem>) : ListingPageEvent {
            constructor(data: DownloadItem) : this(listOf(data))
        }
    }

    data class OnChangePage(val data: MainScreenState.Page) : MainScreenEvent
}
