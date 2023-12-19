package com.chrrissoft.downloadmanager.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.ImeAction
import com.chrrissoft.downloadmanager.ui.components.app.MyTextField

@Composable
fun TitleInput(
    state: String,
    onChangeState: (String) -> Unit
) {
    MyTextField(
        value = state,
        label = { Text(text = "Title") },
        onValueChange = {onChangeState(it)},
        keyboardOptions = remember { KeyboardOptions(imeAction = ImeAction.Done) },
    )
}
