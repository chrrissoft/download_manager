package com.chrrissoft.downloadmanager.usecases.interfaces

import com.chrrissoft.downloadmanager.entities.DownloadItem
import com.chrrissoft.downloadmanager.entities.DownloadQuery
import com.chrrissoft.downloadmanager.entities.ResState
import kotlinx.coroutines.flow.Flow

interface GetDownloadItemsUseCase {
    operator fun invoke(data: DownloadQuery) : Flow<ResState<List<ResState<DownloadItem>>>>
}
