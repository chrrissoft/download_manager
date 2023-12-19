package com.chrrissoft.downloadmanager.ui.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import com.chrrissoft.downloadmanager.ui.MainScreenEvent
import com.chrrissoft.downloadmanager.ui.MainScreenEvent.OnChangePage
import com.chrrissoft.downloadmanager.ui.MainScreenState
import com.chrrissoft.downloadmanager.ui.MainScreenState.Page.Companion.pages
import com.chrrissoft.downloadmanager.ui.components.app.DownloadManagerSnackbar
import com.chrrissoft.downloadmanager.ui.components.app.Screen
import com.chrrissoft.downloadmanager.ui.theme.navigationBarItemColors

@Composable
fun MainScreen(
    state: MainScreenState,
    onEvent: (MainScreenEvent) -> Unit,
) {
    Screen(
        title = "Download Manager",
        content = { ScreenContent(state, onEvent, state.snackbar) },
        padding = true,
        bottomBar = {
            NavigationBar(containerColor = colorScheme.primaryContainer, contentColor = colorScheme.onPrimaryContainer) {
                pages.forEach {
                    NavigationBarItem(
                        selected = it == state.page,
                        onClick = { onEvent(OnChangePage(it)) },
                        icon = { Icon(it.icon, (null)) },
                        colors = navigationBarItemColors
                    )
                }
            }
        }
    )
}
