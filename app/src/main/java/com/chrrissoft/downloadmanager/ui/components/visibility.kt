package com.chrrissoft.downloadmanager.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.chrrissoft.downloadmanager.Values
import com.chrrissoft.downloadmanager.ui.components.app.MyInputChip
import com.chrrissoft.downloadmanager.ui.components.app.MyModalBottomSheet
import com.chrrissoft.downloadmanager.ui.components.app.MyTextField
import com.chrrissoft.downloadmanager.ui.theme.myFilledIconToggleButtonColors
import com.chrrissoft.downloadmanager.ui.theme.textFieldDisableColors

@Composable
fun VisibilityChooser(
    state: Pair<String, Int>,
    onChangeState: (Pair<String, Int>) -> Unit,
    modifier: Modifier = Modifier,
) {
    val (showModal, changeShowModal) = remember { mutableStateOf(value = false) }

    if (showModal) {
        VisibilityListingModal(
            state = state,
            onChangeState = onChangeState,
            onDismissRequest = { changeShowModal(false) },
        )
    }

    MyTextField(
        value = state.first,
        onValueChange = {},
        enabled = false,
        colors = textFieldDisableColors,
        label = { Text(text = "Notification visibility") },
        trailingIcon = {
            FilledTonalIconToggleButton(
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
private fun VisibilityListingModal(
    state: Pair<String, Int>,
    onChangeState: (Pair<String, Int>) -> Unit,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
) {
    MyModalBottomSheet(
        title = "Notification visibility",
        onDismissRequest = onDismissRequest,
        content = { VisibilityListing(state = state, onChangeState = onChangeState) },
        modifier = modifier
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun VisibilityListing(
    state: Pair<String, Int>,
    onChangeState: (Pair<String, Int>) -> Unit,
    modifier: Modifier = Modifier,
    listing: List<Pair<String, Int>> = Values.visibilities
) {
    LazyColumn(modifier) {
        items(listing) {
            MyInputChip(
                selected = state == it,
                onClick = { onChangeState(it) },
                label = { Text(text = it.first) }
            )
        }
    }
}
