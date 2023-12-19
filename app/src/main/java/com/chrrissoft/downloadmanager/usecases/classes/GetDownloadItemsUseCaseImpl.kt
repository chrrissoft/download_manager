package com.chrrissoft.downloadmanager.usecases.classes

import android.app.DownloadManager
import android.app.DownloadManager.*
import androidx.core.database.getDoubleOrNull
import androidx.core.database.getIntOrNull
import androidx.core.database.getStringOrNull
import com.chrrissoft.downloadmanager.entities.DownloadItem
import com.chrrissoft.downloadmanager.entities.DownloadQuery
import com.chrrissoft.downloadmanager.entities.DownloadQuery.Companion.toManagerQuery
import com.chrrissoft.downloadmanager.entities.DownloadState.Companion.DownloadState
import com.chrrissoft.downloadmanager.entities.ResState
import com.chrrissoft.downloadmanager.entities.ResState.Success
import com.chrrissoft.downloadmanager.usecases.interfaces.GetDownloadItemsUseCase
import com.chrrissoft.downloadmanager.utils.FlowUtils.ResFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDownloadItemsUseCaseImpl @Inject constructor(
    private val downloadManager: DownloadManager,
) : GetDownloadItemsUseCase {
    override fun invoke(data: DownloadQuery): Flow<ResState<List<ResState<DownloadItem>>>> {
        return ResFlow {
            val cursor = downloadManager.query(data.toManagerQuery())
            val res = mutableMapOf<Long, ResState<DownloadItem>>()
            cursor.moveToPosition(-1)
            while (true) {
                while (cursor.moveToNext()) {
                    val idColumnIndex = cursor.getColumnIndex(COLUMN_ID)
                    val id = cursor.getLong(idColumnIndex)
                    try {
                        val statusColumn = cursor.getColumnIndex(COLUMN_STATUS)
                        val reasonColumn = cursor.getColumnIndex(COLUMN_REASON)
                        val descriptionColumn = cursor.getColumnIndex(COLUMN_DESCRIPTION)
                        val titleColumn = cursor.getColumnIndex(COLUMN_TITLE)
                        val soFarColumn = cursor.getColumnIndex(COLUMN_BYTES_DOWNLOADED_SO_FAR)
                        val typeColumn = cursor.getColumnIndex(COLUMN_MEDIA_TYPE)
                        val totalColumn = cursor.getColumnIndex(COLUMN_TOTAL_SIZE_BYTES)
                        val uriColumn = cursor.getColumnIndex(COLUMN_URI)

                        val status = cursor.getInt(statusColumn)
                        val reason = cursor.getInt(reasonColumn)

                        res[id] = run {
                                DownloadItem(
                                    id = id,
                                    state = DownloadState(status, reason),
                                    mimeType = cursor.getStringOrNull(typeColumn),
                                    description = cursor.getStringOrNull(descriptionColumn),
                                    title = cursor.getStringOrNull(titleColumn),
                                    soFarKb = cursor.getDoubleOrNull(soFarColumn),
                                    uri = cursor.getStringOrNull(uriColumn),
                                    totalKb = cursor.getDoubleOrNull(totalColumn),
                                ).let { Success(it) }
                            }
                    } catch (e: Throwable) {
                        res[id] = ResState.Error(e)
                    }
                }
                emit(Success(res.toList().map { it.second }))
                cursor.moveToPosition(-1)
                delay(timeMillis = 1000)
            }
        }
    }
}
