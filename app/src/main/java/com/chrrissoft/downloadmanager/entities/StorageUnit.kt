package com.chrrissoft.downloadmanager.entities

sealed interface StorageUnit {
    val value: Double

    @JvmInline
    value class KB(override val value: Double) : StorageUnit

    @JvmInline
    value class MB(override val value: Double) : StorageUnit

    @JvmInline
    value class GB(override val value: Double) : StorageUnit
}