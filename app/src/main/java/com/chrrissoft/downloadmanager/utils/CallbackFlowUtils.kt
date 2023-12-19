package com.chrrissoft.downloadmanager.utils

import com.chrrissoft.downloadmanager.entities.ResState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

object CallbackFlowUtils {
    @Suppress("FunctionName")
    fun <T> CallbackResFlow(
        dispatcher: CoroutineDispatcher = Dispatchers.Default,
        block: suspend FlowCollector<ResState<T>>.() -> Unit
    ): Flow<ResState<T>> {
        return flow {
            emit(ResState.Loading)
            block()
        }.catch { emit(ResState.Error(it)) }.flowOn(dispatcher)
    }
}