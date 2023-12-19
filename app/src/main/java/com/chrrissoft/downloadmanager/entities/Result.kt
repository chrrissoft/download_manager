package com.chrrissoft.downloadmanager.entities

import com.chrrissoft.downloadmanager.utils.Util.debug

sealed interface Result<out R> {
    data class Success<R>(val data: R) : Result<R>

    data class Error(val throwable: Throwable?) : Result<Nothing> {
        init {
            throwable?.printStackTrace() ?: debug(message = this)
        }

        val message get() = throwable?.message ?: "Unknown error"
    }
}
