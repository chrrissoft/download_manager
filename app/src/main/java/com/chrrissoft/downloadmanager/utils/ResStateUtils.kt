package com.chrrissoft.downloadmanager.utils

import com.chrrissoft.downloadmanager.entities.ResState

object ResStateUtils {
    fun <T> ResState<T>.text(
        loading: () -> String = { "Loading" },
        error: (Throwable?) -> String = { it?.message ?: "Unknown error" },
        success: (T) -> String,
    ): String {
        return when (this) {
            ResState.Loading -> loading()
            is ResState.Error -> error(throwable)
            is ResState.Success -> success(data)
        }
    }

    suspend fun<T> ResState<T>.onSuccess(block: suspend (T) -> Unit): ResState<T> {
        if (this is ResState.Success) block(data)
        return this
    }
}
