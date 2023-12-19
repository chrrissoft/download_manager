package com.chrrissoft.downloadmanager.usecases.classes

import com.chrrissoft.downloadmanager.db.DownloadRequest.Companion.toCommon
import com.chrrissoft.downloadmanager.db.DownloadRequestDao
import com.chrrissoft.downloadmanager.entities.DownloadRequest
import com.chrrissoft.downloadmanager.entities.ResState
import com.chrrissoft.downloadmanager.entities.ResState.Success
import com.chrrissoft.downloadmanager.usecases.interfaces.GetDownloadRequestsUseCase
import com.chrrissoft.downloadmanager.utils.FlowUtils.ResFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class GetDownloadRequestsUseCaseImpl @Inject constructor(
    private val dao: DownloadRequestDao,
) : GetDownloadRequestsUseCase {
    override fun invoke(): Flow<ResState<List<DownloadRequest>>> {
        return ResFlow {
            dao.get().collect { emit(Success(it.map { it.toCommon() })) }
        }
    }
}
