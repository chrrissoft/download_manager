package com.chrrissoft.downloadmanager.usecases.interfaces

import com.chrrissoft.downloadmanager.entities.DownloadRequest
import com.chrrissoft.downloadmanager.entities.ResState
import kotlinx.coroutines.flow.Flow

interface GetDownloadRequestsUseCase {
    operator fun invoke(): Flow<ResState<List<DownloadRequest>>>
}
