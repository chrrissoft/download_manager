package com.chrrissoft.downloadmanager.ui.components.app

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.InputChipDefaults.inputChipBorder
import androidx.compose.material3.SelectableChipBorder
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.SelectableChipElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Shape
import com.chrrissoft.downloadmanager.ui.theme.inputChipColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyInputChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null,
    avatar: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    shape: Shape = InputChipDefaults.shape,
    colors: SelectableChipColors = inputChipColors,
    elevation: SelectableChipElevation? = InputChipDefaults.inputChipElevation(),
    border: SelectableChipBorder? = inputChipBorder(Transparent, disabledBorderColor = Transparent),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    InputChip(
        selected = selected,
        onClick = onClick,
        label = label,
        enabled = enabled,
        leadingIcon = leadingIcon,
        avatar = avatar,
        trailingIcon = trailingIcon,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        interactionSource = interactionSource,
        modifier = modifier.fillMaxWidth()
    )
}
