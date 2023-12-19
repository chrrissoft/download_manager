package com.chrrissoft.downloadmanager.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Rounded
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.downloadmanager.entities.DownloadItem
import com.chrrissoft.downloadmanager.entities.ResState
import com.chrrissoft.downloadmanager.entities.ResState.Error
import com.chrrissoft.downloadmanager.entities.ResState.Loading
import com.chrrissoft.downloadmanager.entities.ResState.Success
import com.chrrissoft.downloadmanager.ui.components.app.MyTextField


@Composable
fun DownloadItems(
    state: ResState<List<ResState<DownloadItem>>>,
    modifier: Modifier = Modifier,
    onRemove: (DownloadItem) -> Unit,
) {
    when (state) {
        Loading -> {}
        is Error -> {}
        is Success -> DownloadItems(state = state.data, modifier) { onRemove(it) }
    }
}

@Composable
fun DownloadItems(
    state: List<ResState<DownloadItem>>,
    modifier: Modifier = Modifier,
    onRemove: (DownloadItem) -> Unit,
) {
    LazyColumn(modifier) {
        items(state) {
            DownloadItem(it) { onRemove(it) }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun DownloadItem(
    state: ResState<DownloadItem>,
    modifier: Modifier = Modifier,
    onRemove: (DownloadItem) -> Unit,
) {
    when (state) {
        Loading -> {}
        is Error -> {}
        is Success -> {
            DownloadItem(state = state.data, modifier) { onRemove(state.data) }
        }
    }
}


@Composable
fun DownloadItem(
    state: DownloadItem,
    modifier: Modifier = Modifier,
    onRemove: () -> Unit,
) {
    MyTextField(
        value = state.title.toString(),
        onValueChange = {},
        label = { Text(text = state.state.javaClass.simpleName + " · " + state.id) },
        prefix = { Text(text = "${state.soFar.value}/${state.total.value}") },
        trailingIcon = { IconButton(onClick = onRemove) { Icon(Rounded.Delete, (null)) } },
        modifier = modifier,
        enabled = false,
        singleLine = true
    )
}
