package com.chrrissoft.downloadmanager.serializers

import android.net.Uri
import android.net.Uri.parse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = Exception::class)
object UriSerializer : KSerializer<Uri> {
    override fun serialize(encoder: Encoder, value: Uri) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): Uri {
        return try { parse(decoder.decodeString()) }
        catch (e: Throwable) { Uri.EMPTY }
    }
}
