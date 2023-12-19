package com.chrrissoft.downloadmanager.ui.components.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.downloadmanager.ui.theme.DownloadManagerTheme

@Composable
fun App(app: @Composable () -> Unit) {
    DownloadManagerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.onPrimary
        ) { app() }
    }
}
