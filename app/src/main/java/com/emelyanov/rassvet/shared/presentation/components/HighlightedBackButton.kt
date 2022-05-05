package com.emelyanov.rassvet.shared.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.ui.theme.RassvetTheme

@Composable
fun HighlightedBackButton(
    modifier: Modifier = Modifier,
    color: Color = RassvetTheme.colors.surfaceText,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(25.dp))
            .background(RassvetTheme.colors.surfaceBackground)
            .clickable(
                onClick = onClick
            )
            .padding(start = 8.dp, top = 2.dp, end = 8.dp, bottom = 3.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(5.dp,13.dp),
            painter = painterResource(id = R.drawable.ic_left_arrow),
            contentDescription = "Back arrow",
            tint = color
        )

        Text(
            text = "Назад",
            style = RassvetTheme.typography.backButtonText
                .copy(color = color)
        )
    }
}