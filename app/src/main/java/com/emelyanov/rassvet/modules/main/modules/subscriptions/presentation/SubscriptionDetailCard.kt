package com.emelyanov.rassvet.modules.main.modules.subscriptions.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import com.emelyanov.rassvet.R

@Composable
fun SubscriptionDetailCard(
    modifier: Modifier = Modifier
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
                .padding(15.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(45.dp,30.dp),
                        painter = painterResource(id = R.drawable.ic_rassvet_logo),
                        contentDescription = "Logo icon",
                        tint = RassvetTheme.colors.logoColor
                    )

                    Text(
                        text = "Рассвет",
                        style = RassvetTheme.typography.logoText
                            .copy(fontSize = 24.sp, color = RassvetTheme.colors.logoColor)
                    )
                }

                Text(
                    text = "Клубная карта",
                    style = RassvetTheme.typography.cardBody1
                        .copy(color = RassvetTheme.colors.logoColor)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )

                Text(
                    text = "Емельянов Павел",
                    style = RassvetTheme.typography.cardBody1
                        .copy(fontWeight = FontWeight.Bold,color = RassvetTheme.colors.logoColor)
                )
            }
        }
    }
}