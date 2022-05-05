package com.emelyanov.rassvet.modules.core.presentation

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.ui.theme.RassvetTheme

@Composable
fun LaunchingScreen(

) {
    val logoTransition = rememberInfiniteTransition()

    val logoAngle = logoTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500)
        )
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(185.dp,120.dp)
                .rotate(logoAngle.value),
            painter = painterResource(id = R.drawable.ic_rassvet_logo),
            contentDescription = "Logo",
            tint = RassvetTheme.colors.logoColor
        )
    }
}