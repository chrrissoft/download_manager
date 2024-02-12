package com.chrrissoft.downloadmanager.di

import android.net.Uri
import com.chrrissoft.downloadmanager.serializers.UriSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.modules.SerializersModule

@Module
@InstallIn(SingletonComponent::class)
object SerializersModuleModule {
    @Provides
    fun provide(): SerializersModule = SerializersModule {
        contextual(Uri::class, UriSerializer)
    }
}
