package com.emelyanov.rassvet.shared.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.ui.theme.RassvetTheme

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    message: String
) {
    Box(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(20.dp),
            text = message,
            style = RassvetTheme.typography.linkButtonText
                .copy(color = RassvetTheme.colors.error)
        )
    }
}