package com.chrrissoft.downloadmanager.serializers

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = Exception::class)
object ThrowableSerializer : KSerializer<Throwable> {
    override fun serialize(encoder: Encoder, value: Throwable) {
        encoder.encodeString(value.message?:"")
    }

    override fun deserialize(decoder: Decoder): Throwable {
        return Throwable(decoder.decodeString())
    }
}
