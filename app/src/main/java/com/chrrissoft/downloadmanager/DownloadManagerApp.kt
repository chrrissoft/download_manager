package com.chrrissoft.downloadmanager

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.os.Build
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationChannelCompat.Builder
import androidx.core.app.NotificationManagerCompat
import com.chrrissoft.downloadmanager.Constants.NOTIFICATION_CHANNEL_ID
import com.chrrissoft.downloadmanager.Constants.NOTIFICATION_CHANNEL_NAME
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class DownloadManagerApp : Application() {
    @Inject
    lateinit var notificationManager: NotificationManagerCompat

    override fun onCreate() {
        super.onCreate()
        createChannel()
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        val channel = Builder(NOTIFICATION_CHANNEL_ID, IMPORTANCE_HIGH)
            .setName(NOTIFICATION_CHANNEL_NAME)
            .build()
        notificationManager.createNotificationChannel(channel)
    }
}
