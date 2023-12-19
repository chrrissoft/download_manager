package com.chrrissoft.downloadmanager.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.chrrissoft.downloadmanager.entities.DownloadState
import com.chrrissoft.downloadmanager.ui.components.app.MyInputChip
import com.chrrissoft.downloadmanager.ui.components.app.MyModalBottomSheet
import com.chrrissoft.downloadmanager.ui.components.app.MyTextField
import com.chrrissoft.downloadmanager.ui.theme.myFilledIconToggleButtonColors
import com.chrrissoft.downloadmanager.ui.theme.textFieldDisableColors
import com.chrrissoft.downloadmanager.utils.Util.ui

@Composable
fun DownloadState(
    state: Set<DownloadState>,
    onChangeState: (Set<DownloadState>) -> Unit,
    modifier: Modifier = Modifier,
) {
    val (showModal, changeShowModal) = remember { mutableStateOf(value = false) }

    if (showModal) {
        DownloadStatesModal(
            state = state,
            onChangeState = onChangeState,
            onDismissRequest = { changeShowModal(false) },
        )
    }


    MyTextField(
        value = state.joinToString { it::class.java.simpleName },
        onValueChange = {},
        enabled = false,
        colors = textFieldDisableColors,
        singleLine = true,
        label = { Text(text = "Download state filter") },
        trailingIcon = {
            FilledIconToggleButton(
                checked = showModal,
                colors = myFilledIconToggleButtonColors(),
                onCheckedChange = { changeShowModal(it) }
            ) { Icon(Icons.Rounded.ExpandMore, (null)) }
        },
        modifier = modifier.clickable { changeShowModal(true) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadStatesModal(
    state: Set<DownloadState>,
    onChangeState: (Set<DownloadState>) -> Unit,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
) {
    MyModalBottomSheet(
        title = "Download states",
        onDismissRequest = onDismissRequest,
        content = { DownloadStatesList(state = state, onChangeState = onChangeState) },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadStatesList(
    state: Set<DownloadState>,
    onChangeState: (Set<DownloadState>) -> Unit,
    modifier: Modifier = Modifier,
    list: List<DownloadState> = DownloadState.States
) {
    LazyColumn(modifier) {
        items(list) {
            val selected = state.contains(it)
            MyInputChip(
                selected = selected,
                label = { Text(it::class.java.simpleName.ui) },
                onClick = { (if (selected) state.minus(it) else state.plus(it)).let(onChangeState) },
            )
        }
    }
}
