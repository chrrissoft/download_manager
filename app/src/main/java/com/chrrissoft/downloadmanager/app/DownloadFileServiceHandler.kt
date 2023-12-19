package com.chrrissoft.downloadmanager.app

import android.net.Uri.parse
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.os.Message.obtain
import android.os.Messenger
import android.os.RemoteException
import com.chrrissoft.downloadmanager.Constants.ClientMessages.MSG_ENQUEUE_DOWNLOAD
import com.chrrissoft.downloadmanager.Constants.ClientMessages.MSG_REGISTER_CLIENT
import com.chrrissoft.downloadmanager.Constants.ClientMessages.MSG_UNREGISTER_CLIENT
import com.chrrissoft.downloadmanager.Constants.ServiceMessages.MSG_DOWNLOAD_CONNECTION_CONNECTED
import com.chrrissoft.downloadmanager.Constants.ServiceMessages.MSG_DOWNLOAD_CONNECTION_DISCONNECTED
import com.chrrissoft.downloadmanager.Constants.ServiceMessages.MSG_ENQUEUE_DOWNLOAD_ERROR
import com.chrrissoft.downloadmanager.Constants.ServiceMessages.MSG_ENQUEUE_DOWNLOAD_LOADING
import com.chrrissoft.downloadmanager.Constants.ServiceMessages.MSG_ENQUEUE_DOWNLOAD_SUCCESS
import com.chrrissoft.downloadmanager.entities.DownloadRequest
import com.chrrissoft.downloadmanager.entities.ResState
import com.chrrissoft.downloadmanager.entities.Result
import com.chrrissoft.downloadmanager.entities.Result.Success
import com.chrrissoft.downloadmanager.usecases.interfaces.EnqueueDownloadUseCase
import com.chrrissoft.downloadmanager.utils.BundleUtils.Bundle
import com.chrrissoft.downloadmanager.utils.MessengerUtils.Message
import com.chrrissoft.downloadmanager.utils.Util.debug
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class DownloadFileServiceHandler @Inject constructor(
    looper: Looper,
    private val scope: CoroutineScope,
    private val EnqueueDownloadUseCase: EnqueueDownloadUseCase,
) : Handler(looper) {
    private val clients by lazy { mutableMapOf<Int, Messenger>() }

    override fun handleMessage(msg: Message) {
        debug("on handleMessage")
        when (msg.what) {
            MSG_REGISTER_CLIENT -> {
                msg.replyTo?.let {
                    clients[msg.sendingUid] = it
                    it.send(obtain(null, MSG_DOWNLOAD_CONNECTION_CONNECTED))
                }
            }

            MSG_UNREGISTER_CLIENT -> {
                clients.remove(msg.sendingUid)
                    ?.send(obtain(null, MSG_DOWNLOAD_CONNECTION_DISCONNECTED))
            }

            MSG_ENQUEUE_DOWNLOAD -> enqueueDownload(msg, clients)
        }
    }

    private fun enqueueDownload(msg: Message, clients: MutableMap<Int, Messenger>) {
        scope.launch {
            debug(msg.data)
//            debug(msg.obj as? Uris)
//            val requests = msg.data.links.map { DownloadRequest(url = it.toString()) }
//            debug("requests $requests")
//            EnqueueDownloadUseCase(requests).collect {
//                send(clients, msg.sendingUid, it)
//            }
        }
    }

    private fun send(
        clients: MutableMap<Int, Messenger>,
        key: Int,
        res: ResState<Map<DownloadRequest, Result<*>>>
    ) {
        when (res) {
            is ResState.Error -> send(clients, key, Message(MSG_ENQUEUE_DOWNLOAD_ERROR))
            ResState.Loading -> send(clients, key, Message(MSG_ENQUEUE_DOWNLOAD_LOADING))
            is ResState.Success -> {
                val map = res.data
                    .mapKeys { parse(it.key.url) }
                    .mapValues { it.value is Success }
                    Message(MSG_ENQUEUE_DOWNLOAD_SUCCESS, Bundle(map))
                        .let { send(clients, key, it) }
            }
        }
    }


    private fun send(clients: MutableMap<Int, Messenger>, key: Int, msg: Message) {
        try {
            clients[key]?.send(msg)
        } catch (e: RemoteException) {
            clients.remove(key)
        }
    }
}
