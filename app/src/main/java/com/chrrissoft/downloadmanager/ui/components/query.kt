package com.chrrissoft.downloadmanager.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.downloadmanager.entities.DownloadQuery
import com.chrrissoft.downloadmanager.entities.ResState
import com.chrrissoft.downloadmanager.ui.components.app.Card

@Composable
fun DownloadQuery(
    ids: ResState<Set<Long>>,
    state: DownloadQuery,
    onChangeState: (DownloadQuery) -> Unit,
    onRunQuery: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier, title = "Download Query") {
        DownloadState(
            state = state.states,
            onChangeState = { onChangeState(state.copy(states = it)) },
        )
        DownloadIds(
            ids = ids,
            state = state.ids,
            onChangeState = { onChangeState(state.copy(ids = it)) },
        )
        Button(onClick = { onRunQuery() }) {
            Text(text = "Run query")
        }
    }
}
