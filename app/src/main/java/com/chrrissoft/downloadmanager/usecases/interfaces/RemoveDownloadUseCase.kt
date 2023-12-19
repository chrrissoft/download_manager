package com.chrrissoft.downloadmanager.usecases.interfaces

import com.chrrissoft.downloadmanager.entities.DownloadRequest
import com.chrrissoft.downloadmanager.entities.ResState
import com.chrrissoft.downloadmanager.entities.Result
import kotlinx.coroutines.flow.Flow

interface RemoveDownloadUseCase {
    operator fun invoke(vararg data: DownloadRequest) : Flow<ResState<Map<DownloadRequest, Result<*>>>>

    operator fun invoke(data: List<DownloadRequest>) : Flow<ResState<Map<DownloadRequest, Result<*>>>> {
        return invoke(*data.toTypedArray())
    }
}
