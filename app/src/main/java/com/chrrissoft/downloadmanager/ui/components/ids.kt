package com.chrrissoft.downloadmanager.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.chrrissoft.downloadmanager.entities.ResState
import com.chrrissoft.downloadmanager.ui.components.app.MyInputChip
import com.chrrissoft.downloadmanager.ui.components.app.MyModalBottomSheet
import com.chrrissoft.downloadmanager.ui.components.app.MyTextField
import com.chrrissoft.downloadmanager.ui.theme.myFilledIconToggleButtonColors
import com.chrrissoft.downloadmanager.ui.theme.textFieldDisableColors
import com.chrrissoft.downloadmanager.utils.Util.ui

@Composable
fun DownloadIds(
    ids: ResState<Set<Long>>,
    state: Set<Long>,
    onChangeState: (Set<Long>) -> Unit,
    modifier: Modifier = Modifier,
) {
    val (showModal, changeShowModal) = remember { mutableStateOf(value = false) }

    if (showModal) {
        IdsModal(
            state = state,
            onChangeState = onChangeState,
            ids = ids,
            onDismissRequest = { changeShowModal(false) },
        )
    }

    MyTextField(
        value = state.joinToString { it.toString() },
        onValueChange = {},
        enabled = false,
        colors = textFieldDisableColors,
        label = { Text(text = "Download ids filter") },
        trailingIcon = {
            FilledIconToggleButton(
                checked = showModal,
                colors = myFilledIconToggleButtonColors(),
                onCheckedChange = { changeShowModal(it) }
            ) { Icon(Icons.Rounded.ExpandMore, (null)) }
        },
        modifier = modifier.clickable { changeShowModal(true) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdsModal(
    state: Set<Long>,
    onChangeState: (Set<Long>) -> Unit,
    ids: ResState<Set<Long>>,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
) {
    MyModalBottomSheet(
        title = "Download ids",
        onDismissRequest = onDismissRequest,
        content = { IdsSet(ids = ids, state = state, onChangeState = onChangeState) },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdsSet(
    state: Set<Long>,
    onChangeState: (Set<Long>) -> Unit,
    modifier: Modifier = Modifier,
    ids: ResState<Set<Long>>,
) {
    when(ids) {
        is ResState.Error -> {}
        ResState.Loading -> {}
        is ResState.Success -> {
            LazyColumn(modifier) {
                items(ids.data.toList()) {
                    val selected = state.contains(it)
                    MyInputChip(
                        selected = selected,
                        label = { Text(it.toString()) },
                        onClick = { (if (selected) state.minus(it) else state.plus(it)).let(onChangeState) },
                    )
                }
            }
        }
    }
}
