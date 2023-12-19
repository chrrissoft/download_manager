package com.chrrissoft.downloadmanager.utils

import com.chrrissoft.downloadmanager.entities.Result

object ResultUtils {
    suspend fun<T> Result<T>.onSuccess(block: suspend (T) -> Unit): Result<T> {
        if (this is Result.Success) block(data)
        return this
    }
}
