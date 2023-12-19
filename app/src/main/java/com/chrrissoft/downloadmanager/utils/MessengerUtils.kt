package com.chrrissoft.downloadmanager.utils

import android.os.Bundle
import android.os.Message
import android.os.Messenger

object MessengerUtils {
    fun Messenger.send(message: Message, replay: Messenger) {
        replay.let { message.replyTo = it }
        send(message)
    }

    fun Message(msg: Int, bundle: Bundle? = null): Message {
        return Message.obtain(null, msg).apply {
            bundle?.let { data = it }
        }
    }
}
