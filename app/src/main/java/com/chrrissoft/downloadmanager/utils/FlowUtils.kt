package com.chrrissoft.downloadmanager.utils

import com.chrrissoft.downloadmanager.entities.DownloadRequest
import com.chrrissoft.downloadmanager.entities.ResState
import com.chrrissoft.downloadmanager.entities.Result
import com.chrrissoft.downloadmanager.entities.Result.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

object FlowUtils {
    @Suppress("FunctionName")
    fun DownloadRequestsFlow(
        block: suspend FlowCollector<ResState<Map<DownloadRequest, Result<*>>>>.(MutableMap<DownloadRequest, Result<*>>) -> Unit
    ): Flow<ResState<Map<DownloadRequest, Result<*>>>> {
        return ResFlow {
            val res = mutableMapOf<DownloadRequest, Result<*>>()
            block(res)
            if (res.isNotEmpty())
                if (res.all { it.value is Success }) emit(ResState.Success(res))
                else emit(ResState.Error(Throwable("None request was successfully on operation")))
            else emit(ResState.Error(Throwable("Empty data receiver")))
        }
    }

    @Suppress("FunctionName")
    fun <T> ResFlow(
        ctx: CoroutineContext = Default,
        block: suspend FlowCollector<ResState<T>>.() -> Unit
    ): Flow<ResState<T>> {
        return flow {
            emit(ResState.Loading)
            block()
        }.catch { emit(ResState.Error(it)) }.flowOn(ctx)
    }
}
