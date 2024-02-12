package com.chrrissoft.downloadmanager.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.app.NotificationCompat
import com.chrrissoft.downloadmanager.Constants
import com.chrrissoft.downloadmanager.R
import com.chrrissoft.downloadmanager.utils.Util.debug

@SuppressLint("MissingPermission")
object NotificationManagerUtils {
    fun generalNotification(
        ctx: Context,
        title: String,
        subText: String? = null,
        text: String? = null,
        icon: Int = R.mipmap.ic_launcher_round,
        channel: String = Constants.NOTIFICATION_CHANNEL_ID,
    ): NotificationCompat.Builder {
//        val largeIcon = BitmapFactory.decodeResource(ctx.resources, R.mipmap.ic_launcher_round)
        debug(message = "notification - Melissa")
        return NotificationCompat.Builder(ctx, channel)
            .setContentTitle(title)
            .setOnlyAlertOnce(true)
            .setSmallIcon(icon)
//            .setLargeIcon(largeIcon)
            .setContentText(text)
            .setSubText(subText)
    }

}
