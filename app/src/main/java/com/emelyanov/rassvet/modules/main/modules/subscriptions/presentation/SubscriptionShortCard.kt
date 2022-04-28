package com.emelyanov.rassvet.modules.main.modules.subscriptions.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.ui.theme.RassvetTheme

@Composable
fun SubscriptionShortCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .fillMaxWidth()
                .height(maxWidth * 0.6f)
                .background(
                    brush = Brush.linearGradient(
                        RassvetTheme.colors.layoutGradientBackground,
                        start = Offset(0f, Float.POSITIVE_INFINITY),
                        end = Offset(Float.POSITIVE_INFINITY, 0f)
                    )
                )
                .clickable(
                    onClick = onClick
                )
                .padding(15.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Секция",
                    style = RassvetTheme.typography.cardSubtitle
                        .copy(color = RassvetTheme.colors.logoColor)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            }
        }
    }
}