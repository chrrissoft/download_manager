package com.chrrissoft.downloadmanager.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.chrrissoft.downloadmanager.ui.theme.inputChipColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestrictionsChooser(
    restriction0: Boolean,
    restriction0Label: String,
    restriction0Icon: ImageVector,
    onChangeRestriction0: (Boolean) -> Unit,
    restriction1: Boolean,
    restriction1Label: String,
    restriction1Icon: ImageVector,
    onChangeRestriction1: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        InputChip(
            selected = restriction0,
            onClick = { onChangeRestriction0(!restriction0) },
            label = { Text(text = restriction0Label) },
            trailingIcon = { Icon(imageVector = restriction0Icon, contentDescription = null) },
            colors = inputChipColors,
            border = InputChipDefaults.inputChipBorder(Color.Transparent),
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.weight(.05f))
        InputChip(
            selected = restriction1,
            onClick = { onChangeRestriction1(!restriction1) },
            label = { Text(text = restriction1Label) },
            trailingIcon = { Icon(imageVector = restriction1Icon, contentDescription = null) },
            colors = inputChipColors,
            border = InputChipDefaults.inputChipBorder(Color.Transparent),
            modifier = Modifier.weight(1f)
        )
    }
}
