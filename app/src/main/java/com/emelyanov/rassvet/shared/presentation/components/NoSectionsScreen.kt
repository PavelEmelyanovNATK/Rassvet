package com.emelyanov.rassvet.shared.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.ui.theme.RassvetTheme

@Composable
fun NoSubscriptionsScreen(
    modifier: Modifier = Modifier,
    onSubscriptionsClick: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.width(IntrinsicSize.Min)
        ) {
            Text(
                text = "Похоже, что вы пока не записаны ни на одну секцию...",
                style = RassvetTheme.typography.cardBody1
                    .copy(
                        fontWeight = FontWeight.SemiBold,
                        color = RassvetTheme.colors.surfaceText,
                        textAlign = TextAlign.Center
                    )
            )

            Spacer(Modifier.height(25.dp))

            GradientButton(
                modifier = Modifier.width(190.dp),
                text = "Записаться",
                onClick = onSubscriptionsClick,
                gradient = RassvetTheme.colors.positiveButton
            )
        }
    }
}