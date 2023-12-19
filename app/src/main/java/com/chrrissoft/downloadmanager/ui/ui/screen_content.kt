package com.chrrissoft.downloadmanager.ui.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.downloadmanager.ui.MainScreenEvent
import com.chrrissoft.downloadmanager.ui.MainScreenState
import com.chrrissoft.downloadmanager.ui.entities.SnackbarData

@Composable
fun ScreenContent(
    state: MainScreenState,
    onEvent: (MainScreenEvent) -> Unit,
    snackbar: SnackbarData,
    modifier: Modifier = Modifier,
) {
    when (state.page) {
        MainScreenState.Page.REQUEST -> RequestPage(state.requestPage, onEvent, snackbar, modifier)
        MainScreenState.Page.LISTING -> ListingPage(state.listingPage, onEvent, snackbar, modifier)
    }
}
