package com.chrrissoft.downloadmanager.usecases.classes

import android.app.DownloadManager
import com.chrrissoft.downloadmanager.db.DownloadRequestDao
import com.chrrissoft.downloadmanager.entities.DownloadRequest
import com.chrrissoft.downloadmanager.entities.ResState
import com.chrrissoft.downloadmanager.entities.Result
import com.chrrissoft.downloadmanager.usecases.interfaces.EnqueueDownloadUseCase
import com.chrrissoft.downloadmanager.utils.DownloadManagerUtils.enqueue
import com.chrrissoft.downloadmanager.utils.DownloadRequestUtils.toDb
import com.chrrissoft.downloadmanager.utils.FlowUtils.DownloadRequestsFlow
import com.chrrissoft.downloadmanager.utils.ResStateUtils.onSuccess
import com.chrrissoft.downloadmanager.utils.ResultUtils.onSuccess
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EnqueueDownloadUseCaseImpl @Inject constructor(
    private val downloadRequestDao: DownloadRequestDao,
    private val downloadManager: DownloadManager,
) : EnqueueDownloadUseCase {
    override fun invoke(vararg data: DownloadRequest): Flow<ResState<Map<DownloadRequest, Result<*>>>> {
        return DownloadRequestsFlow { res ->
        if (data.isEmpty())
            data.forEach { request ->
                val result = downloadManager.enqueue(request)
                result.onSuccess {
                    downloadRequestDao.save(request.toDb(it))
                    res[request.copy(id = it)] = result
                }
            }
        }
    }
}
