package com.chrrissoft.downloadmanager.utils

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import com.chrrissoft.downloadmanager.Constants.Extras.EXTRA_CLASS_NAME
import com.chrrissoft.downloadmanager.Constants.Extras.EXTRA_LINKS
import com.chrrissoft.downloadmanager.Constants.Extras.EXTRA_PACKAGE_NAME
import kotlinx.serialization.json.Json

object IntentUtils {
    fun Intent.uris(json: Json): List<Uri> {
         return getStringExtra(EXTRA_LINKS)
            ?.let { json.decodeFromString(it) } ?: emptyList()
    }

    val Intent.clientPackage: String get() = getStringExtra(EXTRA_PACKAGE_NAME) ?: ""

    val Intent.clientClass: String get() = getStringExtra(EXTRA_CLASS_NAME) ?: ""

    fun Intent(app: String, component: String): Intent = Intent()
        .apply { this.component = ComponentName(app, component) }
}
