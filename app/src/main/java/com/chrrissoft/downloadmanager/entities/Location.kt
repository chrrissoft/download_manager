package com.chrrissoft.downloadmanager.entities

import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.Q
import android.os.Build.VERSION_CODES.S
import android.os.Environment
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.ui.graphics.vector.ImageVector
import com.chrrissoft.downloadmanager.utils.Util.ui

sealed interface Location {
    val value: String
    val label: String
    val icon: ImageVector

    @JvmInline
    value class MUSIC private constructor(override val value: String = Environment.DIRECTORY_MUSIC) :
        Location {
        override val icon get() = Icons.Rounded.Favorite
        override val label get() = value.ui

        companion object {
            val MUSIC = MUSIC()
        }
    }

    @JvmInline
    value class PODCASTS private constructor(override val value: String = Environment.DIRECTORY_PODCASTS) :
        Location {
        override val icon get() = Icons.Rounded.Favorite
        override val label get() = value.ui

        companion object {
            val PODCASTS = PODCASTS()
        }
    }

    @JvmInline
    value class RINGTONES private constructor(override val value: String = Environment.DIRECTORY_RINGTONES) :
        Location {
        override val icon get() = Icons.Rounded.Favorite
        override val label get() = value.ui

        companion object {
            val RINGTONES = RINGTONES()
        }
    }

    @JvmInline
    value class ALARMS private constructor(override val value: String = Environment.DIRECTORY_ALARMS) :
        Location {
        override val icon get() = Icons.Rounded.Favorite
        override val label get() = value.ui

        companion object {
            val ALARMS = ALARMS()
        }
    }

    @JvmInline
    value class NOTIFICATIONS private constructor(override val value: String = Environment.DIRECTORY_NOTIFICATIONS) :
        Location {
        override val icon get() = Icons.Rounded.Favorite
        override val label get() = value.ui

        companion object {
            val NOTIFICATIONS = NOTIFICATIONS()
        }
    }

    @JvmInline
    value class PICTURES private constructor(override val value: String = Environment.DIRECTORY_PICTURES) :
        Location {
        override val icon get() = Icons.Rounded.Favorite
        override val label get() = value.ui

        companion object {
            val PICTURES = PICTURES()
        }
    }

    @JvmInline
    value class MOVIES private constructor(override val value: String = Environment.DIRECTORY_MOVIES) :
        Location {
        override val icon get() = Icons.Rounded.Favorite
        override val label get() = value.ui

        companion object {
            val MOVIES = MOVIES()
        }
    }

    @JvmInline
    value class DOWNLOADS private constructor(override val value: String = Environment.DIRECTORY_DOWNLOADS) :
        Location {
        override val icon get() = Icons.Rounded.Favorite
        override val label get() = value.ui

        companion object {
            val DOWNLOADS = DOWNLOADS()
        }
    }

    @JvmInline
    value class DCIM private constructor(override val value: String = Environment.DIRECTORY_DCIM) :
        Location {
        override val icon get() = Icons.Rounded.Favorite
        override val label get() = value.ui

        companion object {
            val DCIM = DCIM()
        }
    }

    @JvmInline
    value class DOCUMENTS private constructor(override val value: String = Environment.DIRECTORY_DOCUMENTS) :
        Location {
        override val icon get() = Icons.Rounded.Favorite
        override val label get() = value.ui

        companion object {
            val DOCUMENTS = DOCUMENTS()
        }
    }

    @JvmInline
    @RequiresApi(Q)
    value class SCREENSHOTS private constructor(override val value: String = Environment.DIRECTORY_SCREENSHOTS) :
        Location {
        override val icon get() = Icons.Rounded.Favorite
        override val label get() = value.ui

        companion object {
            val SCREENSHOTS = SCREENSHOTS()
        }
    }

    @JvmInline
    @RequiresApi(Q)
    value class AUDIOBOOKS private constructor(override val value: String = Environment.DIRECTORY_AUDIOBOOKS) :
        Location {
        override val icon get() = Icons.Rounded.Favorite
        override val label get() = value.ui

        companion object {
            val AUDIOBOOKS = AUDIOBOOKS()
        }
    }

    @JvmInline
    @RequiresApi(S)
    value class RECORDINGS private constructor(override val value: String = Environment.DIRECTORY_RECORDINGS) :
        Location {
        override val icon get() = Icons.Rounded.Favorite
        override val label get() = value.ui

        companion object {
            val RECORDINGS = RECORDINGS()
        }
    }

    @JvmInline
    value class Custom(override val value: String = "") : Location {
        override val icon get() = Icons.Rounded.Favorite
        override val label
            get() = "Custom.../".plus(
                value.takeLastWhile { "$it" != "/" }.ui
            )
    }


    companion object {
        val directories = buildList {
            add(Custom())
            add(DOWNLOADS.DOWNLOADS)
            add(MUSIC.MUSIC)
            add(PODCASTS.PODCASTS)
            add(RINGTONES.RINGTONES)
            add(ALARMS.ALARMS)
            add(NOTIFICATIONS.NOTIFICATIONS)
            add(PICTURES.PICTURES)
            add(MOVIES.MOVIES)
            add(DCIM.DCIM)
            add(DOCUMENTS.DOCUMENTS)
            if (SDK_INT >= Q) add(SCREENSHOTS.SCREENSHOTS)
            if (SDK_INT >= Q) add(AUDIOBOOKS.AUDIOBOOKS)
            if (SDK_INT >= S) add(RECORDINGS.RECORDINGS)
        }
    }
}
