package com.chrrissoft.downloadmanager.usecases.classes

import android.app.DownloadManager
import com.chrrissoft.downloadmanager.db.DownloadRequestDao
import com.chrrissoft.downloadmanager.entities.DownloadRequest
import com.chrrissoft.downloadmanager.entities.ResState
import com.chrrissoft.downloadmanager.entities.ResState.Success
import com.chrrissoft.downloadmanager.usecases.interfaces.RemoveDownloadUseCase
import com.chrrissoft.downloadmanager.utils.DownloadRequestUtils.toDb
import com.chrrissoft.downloadmanager.entities.Result
import com.chrrissoft.downloadmanager.utils.FlowUtils.DownloadRequestsFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveDownloadUseCaseImpl @Inject constructor(
    private val downloadRequestDao: DownloadRequestDao,
    private val downloadManager: DownloadManager,
) : RemoveDownloadUseCase {
    override fun invoke(vararg data: DownloadRequest): Flow<ResState<Map<DownloadRequest, Result<*>>>> {
        return DownloadRequestsFlow { res ->
            downloadManager.remove(*data.map { it.id }.toLongArray())
            data.forEach { request ->
                downloadRequestDao.delete(request.toDb(request.id))
                res[request] = Result.Success(null)
            }
        }
    }
}
