package com.chrrissoft.downloadmanager.utils

import android.content.Context
import android.util.Log
import androidx.compose.material3.*
import com.chrrissoft.downloadmanager.utils.ContextUtils.toast
import java.util.*

object Util {
    val String.ui
        get() = replace("_", " ").lowercase()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }

    @Suppress("FunctionName")
    inline fun <R> Try(
        ctx: Context? = null,
        block: () -> R?
    ): R? {
        return try {
            block()
        } catch (e: Throwable) {
            val error = e.message ?: "Unknown Error"
            ctx?.toast(message = "Error $error")
            null
        }
    }

    fun Any.debug(message: Any?, tag: Any = this) {
        Log.d(tag::class.java.simpleName, message.toString())
    }
}
