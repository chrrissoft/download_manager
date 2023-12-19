package com.chrrissoft.downloadmanager.ui.theme

import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.InputChipDefaults.inputChipColors
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.chrrissoft.downloadmanager.ui.components.app.AlertDialogColors
import androidx.compose.material3.NavigationBarItemDefaults.colors as navigationBarItemColors

@OptIn(ExperimentalMaterial3Api::class)
val centerAlignedTopAppBarColors
    @Composable get() = run {
        centerAlignedTopAppBarColors(
            containerColor = colorScheme.primaryContainer,
            navigationIconContentColor = colorScheme.primary,
            titleContentColor = colorScheme.primary,
            actionIconContentColor = colorScheme.primary,
        )
    }

val navigationBarItemColors
    @Composable get() = run {
        navigationBarItemColors(
            selectedIconColor = colorScheme.onPrimary,
            selectedTextColor = colorScheme.primary,
            indicatorColor = colorScheme.primary,
            unselectedIconColor = colorScheme.primary.copy(.5f),
            unselectedTextColor = colorScheme.primary.copy(.5f),
        )
    }

val navigationDrawerItemColors
    @Composable get() = run {
        NavigationDrawerItemDefaults.colors(
            selectedContainerColor = colorScheme.primary,
            unselectedContainerColor = colorScheme.onPrimary,
            selectedIconColor = colorScheme.onPrimary,
            unselectedIconColor = colorScheme.secondary.copy(.5f),
            selectedTextColor = colorScheme.onPrimary,
            unselectedTextColor = colorScheme.secondary.copy(.5f),
        )
    }

@OptIn(ExperimentalMaterial3Api::class)
val inputChipColors
    @Composable get() = run {
        inputChipColors(
            containerColor = colorScheme.primaryContainer,
            labelColor = colorScheme.primary,
            leadingIconColor = colorScheme.primary,
            trailingIconColor = colorScheme.primary,
            disabledContainerColor = colorScheme.primaryContainer.copy(.5f),
            disabledLabelColor = colorScheme.secondary.copy(.5f),
            disabledLeadingIconColor = colorScheme.secondary.copy(.5f),
            disabledTrailingIconColor = colorScheme.secondary.copy(.5f),
            selectedContainerColor = colorScheme.primary,
            disabledSelectedContainerColor = colorScheme.errorContainer,
            selectedLabelColor = colorScheme.onPrimary,
            selectedLeadingIconColor = colorScheme.onPrimary,
            selectedTrailingIconColor = colorScheme.onPrimary,
        )
    }

@OptIn(ExperimentalMaterial3Api::class)
val inputChipColorsVariant
    @Composable get() = run {
        inputChipColors(
            containerColor = colorScheme.onPrimary,
            labelColor = colorScheme.primary,
            leadingIconColor = colorScheme.primary,
            trailingIconColor = colorScheme.primary,
            disabledContainerColor = colorScheme.primaryContainer.copy(.5f),
            disabledLabelColor = colorScheme.secondary.copy(.5f),
            disabledLeadingIconColor = colorScheme.secondary.copy(.5f),
            disabledTrailingIconColor = colorScheme.secondary.copy(.5f),
            selectedContainerColor = colorScheme.primary,
            disabledSelectedContainerColor = colorScheme.errorContainer,
            selectedLabelColor = colorScheme.onPrimary,
            selectedLeadingIconColor = colorScheme.onPrimary,
            selectedTrailingIconColor = colorScheme.onPrimary,
        )
    }

val cardColors
    @Composable get() = run {
        cardColors(
            containerColor = colorScheme.primaryContainer,
            contentColor = colorScheme.onPrimaryContainer,
        )
    }

@Composable
fun textFieldColors(
    focusedTextColor: Color = colorScheme.onPrimaryContainer,
    unfocusedTextColor: Color = colorScheme.onPrimaryContainer,
    disabledTextColor: Color = colorScheme.onPrimaryContainer,
    focusedContainerColor: Color = colorScheme.primaryContainer,
    unfocusedContainerColor: Color = colorScheme.primaryContainer,
    disabledContainerColor: Color = colorScheme.primaryContainer,
    cursorColor: Color = colorScheme.onPrimaryContainer,
    focusedIndicatorColor: Color = Color.Transparent,
    unfocusedIndicatorColor: Color = Color.Transparent,
    disabledIndicatorColor: Color = Color.Transparent,
    focusedTrailingIconColor: Color = colorScheme.onPrimaryContainer,
    unfocusedTrailingIconColor: Color = colorScheme.onPrimaryContainer,
    disabledTrailingIconColor: Color = colorScheme.onPrimaryContainer,
    focusedLeadingIconColor: Color = colorScheme.onPrimaryContainer,
    unfocusedLeadingIconColor: Color = colorScheme.onPrimaryContainer,
    disabledLeadingIconColor: Color = colorScheme.onPrimaryContainer,
    focusedLabelColor: Color = colorScheme.onPrimaryContainer.copy(.6f),
    unfocusedLabelColor: Color = colorScheme.onPrimaryContainer.copy(.6f),
    disabledLabelColor: Color = colorScheme.onPrimaryContainer.copy(.6f),
): TextFieldColors {
    return TextFieldDefaults.colors(
        focusedTextColor = focusedTextColor,
        unfocusedTextColor = unfocusedTextColor,
        disabledTextColor = disabledTextColor,
        focusedContainerColor = focusedContainerColor,
        unfocusedContainerColor = unfocusedContainerColor,
        disabledContainerColor = disabledContainerColor,
        cursorColor = cursorColor,
        focusedIndicatorColor = focusedIndicatorColor,
        unfocusedIndicatorColor = unfocusedIndicatorColor,
        disabledIndicatorColor = disabledIndicatorColor,
        focusedTrailingIconColor = focusedTrailingIconColor,
        unfocusedTrailingIconColor = unfocusedTrailingIconColor,
        disabledTrailingIconColor = disabledTrailingIconColor,
        focusedLeadingIconColor = focusedLeadingIconColor,
        unfocusedLeadingIconColor = unfocusedLeadingIconColor,
        disabledLeadingIconColor = disabledLeadingIconColor,
        focusedLabelColor = focusedLabelColor,
        unfocusedLabelColor = unfocusedLabelColor,
        disabledLabelColor = disabledLabelColor,
    )
}

val textFieldDisableColors
    @Composable get() = run {
        textFieldColors(
            disabledContainerColor = colorScheme.primaryContainer,
            disabledLabelColor = colorScheme.onPrimaryContainer.copy(.6f),
            disabledTextColor = colorScheme.onPrimaryContainer,
            disabledTrailingIconColor = colorScheme.onPrimaryContainer,
        )
    }

val alertDialogColors
    @Composable get() = run {
        AlertDialogColors.colors(
            containerColor = colorScheme.primaryContainer,
            iconContentColor = colorScheme.onPrimaryContainer,
            titleContentColor = colorScheme.onPrimaryContainer,
            textContentColor = colorScheme.onPrimaryContainer,
        )
    }

@Composable
fun myFilledIconToggleButtonColors(
    containerColor: Color = Color.Transparent,
    contentColor: Color = colorScheme.onPrimaryContainer,
    disabledContainerColor: Color = colorScheme.errorContainer,
    disabledContentColor: Color = colorScheme.onErrorContainer,
    checkedContainerColor: Color = colorScheme.onPrimaryContainer,
    checkedContentColor: Color = colorScheme.onPrimary,
): IconToggleButtonColors {
    return IconButtonDefaults.filledIconToggleButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        checkedContainerColor = checkedContainerColor,
        checkedContentColor = checkedContentColor,
    )
}