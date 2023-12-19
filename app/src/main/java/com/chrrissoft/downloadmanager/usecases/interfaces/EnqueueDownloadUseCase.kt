package com.chrrissoft.downloadmanager.usecases.interfaces

import com.chrrissoft.downloadmanager.entities.DownloadRequest
import com.chrrissoft.downloadmanager.entities.ResState
import com.chrrissoft.downloadmanager.entities.ResState.Companion.map
import com.chrrissoft.downloadmanager.entities.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface EnqueueDownloadUseCase {
    operator fun invoke(vararg data: DownloadRequest):
            Flow<ResState<Map<DownloadRequest, Result<*>>>>

    operator fun invoke(single: DownloadRequest): Flow<ResState<Pair<DownloadRequest, Result<*>>>> {
        return invoke(data = listOf(single))
            .map { res -> res.map { it.entries.first().toPair() } }
    }

    operator fun invoke(data: List<DownloadRequest>):
            Flow<ResState<Map<DownloadRequest, Result<*>>>> = invoke(*data.toTypedArray())
}
