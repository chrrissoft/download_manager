package com.chrrissoft.downloadmanager.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.downloadmanager.entities.DownloadRequest
import com.chrrissoft.downloadmanager.ui.theme.cardColors

@Composable
fun DownloadRequest(
    state: DownloadRequest,
    onChangeState: (DownloadRequest) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = cardColors,
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            FileNameInput(
                state = state.name,
                onChangeState = { onChangeState(state.copy(name = it)) })
            UriInput(
                state = state.url,
                onChangeState = { onChangeState(state.copy(url = it)) })
            Spacer(modifier = Modifier.height(10.dp))
            TitleInput(
                state = state.title,
                onChangeState = { onChangeState(state.copy(title = it)) })
            Spacer(modifier = Modifier.height(10.dp))
            DescriptionInput(
                state = state.description,
                onChangeState = { onChangeState(state.copy(description = it)) })
            Spacer(modifier = Modifier.height(10.dp))
            LocationChooser(
                state = state.location,
                onChangeState = { onChangeState(state.copy(location = it)) })
            Spacer(modifier = Modifier.height(10.dp))
            NetworkTypeChooser(
                state = state.networkType,
                onChangeState = { onChangeState(state.copy(networkType = it)) })
            Spacer(modifier = Modifier.height(10.dp))
            VisibilityChooser(
                state = state.visibility,
                onChangeState = { onChangeState(state.copy(visibility = it)) })
            // RestrictionsChooser - charging && idle
            Box {
                RestrictionsChooser(
                    restriction0 = state.requiresCharging,
                    restriction0Label = "Charging",
                    restriction0Icon = Icons.Rounded.Favorite,
                    onChangeRestriction0 = { onChangeState(state.copy(requiresCharging = it)) },
                    restriction1 = state.requiresDeviceIdle,
                    restriction1Label = "Device Idle",
                    restriction1Icon = Icons.Rounded.Favorite,
                    onChangeRestriction1 = { onChangeState(state.copy(requiresDeviceIdle = it)) },
                )
            }

            // RestrictionsChooser - Metered && Roaming
            Box {
                RestrictionsChooser(
                    restriction0 = state.allowedOverMetered,
                    restriction0Label = "Over metered",
                    restriction0Icon = Icons.Rounded.Favorite,
                    onChangeRestriction0 = { onChangeState(state.copy(allowedOverMetered = it)) },
                    restriction1 = state.allowedOverRoaming,
                    restriction1Label = "Over roaming",
                    restriction1Icon = Icons.Rounded.Favorite,
                    onChangeRestriction1 = { onChangeState(state.copy(allowedOverRoaming = it)) },
                )
            }
        }
    }
}
