package com.chrrissoft.downloadmanager.app

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Messenger
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DownloadFileService : Service() {
    @Inject
    lateinit var messenger: Messenger

    override fun onBind(intent: Intent): IBinder = messenger.binder
}
