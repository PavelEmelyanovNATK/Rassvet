package com.emelyanov.rassvet.shared.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.ui.theme.RassvetTheme

@Composable
fun SolidBackgroundView(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RassvetTheme.colors.layoutBackground)
    ){
        Image(
            modifier = Modifier.matchParentSize(),
            painter = painterResource(id = R.drawable.bg_icons),
            contentDescription = "Background Icons",
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter
                .tint(color = RassvetTheme.colors.layoutBackgroundIcons
                    .copy(alpha = 0.07f))
        )

        content()
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
private fun Preview() {
    RassvetTheme {
        SolidBackgroundView {

        }
    }
}