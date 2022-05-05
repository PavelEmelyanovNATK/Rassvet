package com.emelyanov.rassvet.shared.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.ui.theme.RassvetTheme

@Composable
fun GradientButton(
    modifier: Modifier = Modifier,
    text: String = "Button",
    onClick: () -> Unit,
    textStyle: TextStyle = RassvetTheme.typography.buttonText
        .copy(RassvetTheme.colors.buttonText),
    gradient: List<Color>
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(25.dp))
            .background(Brush.linearGradient(
                gradient,
                start = Offset(0f, Float.POSITIVE_INFINITY),
                end = Offset(Float.POSITIVE_INFINITY, 0f)
            ))
            .clickable(onClick = onClick)
            .padding(start = 30.dp, top = 8.dp, bottom = 9.dp, end = 30.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = textStyle
        )
    }
}