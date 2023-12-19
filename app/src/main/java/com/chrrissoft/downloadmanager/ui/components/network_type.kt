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
import com.chrrissoft.downloadmanager.entities.NetworkType
import com.chrrissoft.downloadmanager.ui.components.app.MyInputChip
import com.chrrissoft.downloadmanager.ui.components.app.MyModalBottomSheet
import com.chrrissoft.downloadmanager.ui.components.app.MyTextField
import com.chrrissoft.downloadmanager.ui.theme.myFilledIconToggleButtonColors
import com.chrrissoft.downloadmanager.ui.theme.textFieldDisableColors
import com.chrrissoft.downloadmanager.utils.Util.ui

@Composable
fun NetworkTypeChooser(
    state: NetworkType,
    onChangeState: (NetworkType) -> Unit,
    modifier: Modifier = Modifier,
) {
    val (showModal, changeShowModal) = remember { mutableStateOf(value = false) }

    if (showModal) {
        NetworkTypeModal(
            state = state,
            onChangeState = onChangeState,
            onDismissRequest = { changeShowModal(false) },
        )
    }

    MyTextField(
        value = state.name.ui,
        onValueChange = {},
        enabled = false,
        colors = textFieldDisableColors,
        label = { Text(text = "Network type") },
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
private fun NetworkTypeModal(
    state: NetworkType,
    onChangeState: (NetworkType) -> Unit,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
) {
    MyModalBottomSheet(
        title = "Network type",
        onDismissRequest = onDismissRequest,
        content = { NetworkTypeListing(state = state, onChangeState = onChangeState) },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NetworkTypeListing(
    state: NetworkType,
    onChangeState: (NetworkType) -> Unit,
    modifier: Modifier = Modifier,
    list: List<NetworkType> = NetworkType.types,
) {
    LazyColumn(modifier) {
        items(list) {
            MyInputChip(
                selected = it == state,
                onClick = { onChangeState(it) },
                label = { Text(text = it.name.ui) },
                leadingIcon = { Icon(it.icon, (null)) },
            )
        }
    }
}
