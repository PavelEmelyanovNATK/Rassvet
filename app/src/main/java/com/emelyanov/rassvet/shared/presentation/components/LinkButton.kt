package com.emelyanov.rassvet.shared.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.emelyanov.rassvet.ui.theme.RassvetTheme

@Composable
fun LinkButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource()}
    Text(
        modifier = modifier
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null
            ),
        text = text,
        style = RassvetTheme.typography.linkButtonText
            .copy(color = RassvetTheme.colors.linkButton)
    )
}