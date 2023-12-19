package com.chrrissoft.downloadmanager.ui.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.downloadmanager.ui.MainScreenEvent.ListingPageEvent
import com.chrrissoft.downloadmanager.ui.MainScreenEvent.ListingPageEvent.OnChangeQuery
import com.chrrissoft.downloadmanager.ui.MainScreenEvent.ListingPageEvent.OnRemove
import com.chrrissoft.downloadmanager.ui.MainScreenEvent.ListingPageEvent.OnRunQuery
import com.chrrissoft.downloadmanager.ui.MainScreenState.ListingPageState
import com.chrrissoft.downloadmanager.ui.components.DownloadItems
import com.chrrissoft.downloadmanager.ui.components.DownloadQuery
import com.chrrissoft.downloadmanager.ui.components.app.DownloadManagerSnackbar
import com.chrrissoft.downloadmanager.ui.components.app.Screen
import com.chrrissoft.downloadmanager.ui.entities.SnackbarData

@Composable
fun ListingPage(
    state: ListingPageState,
    onEvent: (ListingPageEvent) -> Unit,
    snackbar: SnackbarData,
    modifier: Modifier = Modifier,
) {
    Screen(
        modifier = modifier,
        snackbarHost = { DownloadManagerSnackbar(snackbar) },
    ) {
        DownloadQuery(
            state = state.query,
            ids = state.ids,
            onChangeState = { onEvent(OnChangeQuery(it)) },
            onRunQuery = { onEvent(OnRunQuery(state.query)) }
        )
        Spacer(modifier = Modifier.height(10.dp))
        DownloadItems(state = state.list) { onEvent(OnRemove(it)) }
    }
}
