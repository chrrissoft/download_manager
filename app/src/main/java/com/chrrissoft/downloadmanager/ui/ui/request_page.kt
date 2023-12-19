package com.chrrissoft.downloadmanager.ui.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.downloadmanager.ui.MainScreenEvent
import com.chrrissoft.downloadmanager.ui.MainScreenEvent.RequestPageEvent
import com.chrrissoft.downloadmanager.ui.MainScreenEvent.RequestPageEvent.OnChangeRequest
import com.chrrissoft.downloadmanager.ui.MainScreenEvent.RequestPageEvent.OnLaunch
import com.chrrissoft.downloadmanager.ui.MainScreenState
import com.chrrissoft.downloadmanager.ui.MainScreenState.RequestPageState
import com.chrrissoft.downloadmanager.ui.components.DownloadRequest
import com.chrrissoft.downloadmanager.ui.components.app.DownloadManagerSnackbar
import com.chrrissoft.downloadmanager.ui.components.app.Screen
import com.chrrissoft.downloadmanager.ui.entities.SnackbarData

@Composable
fun RequestPage(
    state: RequestPageState,
    onEvent: (RequestPageEvent) -> Unit,
    snackbar: SnackbarData,
    modifier: Modifier = Modifier,
) {
    Screen(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(OnLaunch(listOf(state.request))) }) {
                Icon(Icons.Rounded.Download, (null))
            }
        },
        snackbarHost = { DownloadManagerSnackbar(snackbar) },
        content = {
            DownloadRequest(
                state = state.request,
                onChangeState = { onEvent(OnChangeRequest(it)) }
            )
        }
    )
}
