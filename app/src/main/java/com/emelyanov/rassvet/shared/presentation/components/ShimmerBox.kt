package com.emelyanov.rassvet.shared.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.emelyanov.rassvet.ui.theme.RassvetTheme

@Composable
fun ShimmerBox(
    modifier: Modifier = Modifier,
    cornersColor: Color = RassvetTheme.colors.shimmerCornersColor,
    centerColor: Color = RassvetTheme.colors.shimmerCenterColor
) {
    val shimmerTransition = rememberInfiniteTransition()
    val gradientCenterOffset = shimmerTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    0f to cornersColor,
                    gradientCenterOffset.value to centerColor,
                    1f to cornersColor,
                    start = Offset.Zero,
                    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                )
            )
    )

}

@Composable
fun ShimmerBox(
    modifier: Modifier = Modifier,
    cornersColor: Color = RassvetTheme.colors.shimmerCornersColor,
    centerColor: Color = RassvetTheme.colors.shimmerCenterColor,
    content: @Composable (modifier: Modifier) -> Unit,
) {
    Box(
        modifier = modifier
    ) {
        content(
            Modifier.alpha(0f)
        )

        ShimmerBox(
            modifier = Modifier.matchParentSize(),
            cornersColor = cornersColor,
            centerColor = centerColor
        )
    }
}