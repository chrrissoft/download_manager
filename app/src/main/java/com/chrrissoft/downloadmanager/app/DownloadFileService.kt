package com.chrrissoft.downloadmanager.app

import android.app.Service
import android.content.Intent
import android.content.Intent.EXTRA_PACKAGE_NAME
import android.net.Uri
import android.net.Uri.parse
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import com.chrrissoft.downloadmanager.Constants.Extras.EXTRA_LINKS_RES
import com.chrrissoft.downloadmanager.entities.DownloadRequest
import com.chrrissoft.downloadmanager.entities.ResState
import com.chrrissoft.downloadmanager.entities.ResState.Error
import com.chrrissoft.downloadmanager.entities.ResState.Loading
import com.chrrissoft.downloadmanager.entities.ResState.Success
import com.chrrissoft.downloadmanager.entities.Result
import com.chrrissoft.downloadmanager.serializers.UriSerializer
import com.chrrissoft.downloadmanager.usecases.interfaces.EnqueueDownloadUseCase
import com.chrrissoft.downloadmanager.utils.IntentUtils.Intent
import com.chrrissoft.downloadmanager.utils.IntentUtils.clientClass
import com.chrrissoft.downloadmanager.utils.IntentUtils.clientPackage
import com.chrrissoft.downloadmanager.utils.IntentUtils.uris
import com.chrrissoft.downloadmanager.utils.NotificationManagerUtils
import com.chrrissoft.downloadmanager.utils.NotificationManagerUtils.generalNotification
import com.chrrissoft.downloadmanager.utils.Util.debug
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Contextual
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

@AndroidEntryPoint
class DownloadFileService : Service() {
    private val map =
        mutableMapOf<String, Map<Uri, ResState<Uri>>>()

    @Inject
    lateinit var EnqueueDownloadUseCase: EnqueueDownloadUseCase

    @Inject
    lateinit var json: Json

    @Inject
    lateinit var scope: CoroutineScope

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(10, generalNotification(this, "Foo").build())
        if (intent == null) return START_NOT_STICKY
        debug(intent.uris(json))
        map[intent.clientClass] = (map[intent.clientClass] ?: emptyMap()) +
                buildMap { intent.uris(json).forEach { put(it, Loading) } }

        val request = intent.uris(json).map { DownloadRequest(it) }

        scope.launch {
            EnqueueDownloadUseCase(request).collect { enqueueRes ->
                debug(enqueueRes)
                if (enqueueRes !is Success) return@collect
                val failures = enqueueRes.data
                    .mapKeys { parse(it.key.url) }
                    .filterValues { it is Result.Error }
                    .mapValues { it.value as Result.Error }

                map[intent.clientClass] = (map[intent.clientClass] ?: emptyMap()) +
                        buildMap { failures.forEach { put(it.key, Error(it.value.throwable)) } }

                Intent(intent.clientPackage, intent.clientClass)
                    .apply { putExtra(EXTRA_LINKS_RES, json.encodeToString(map[intent.clientClass])) }
                    .let { startService(it) }
                debug("after startService")
            }
        }

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? = null
}
