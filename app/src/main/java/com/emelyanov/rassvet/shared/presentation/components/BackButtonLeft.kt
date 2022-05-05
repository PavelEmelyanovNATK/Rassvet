package com.emelyanov.rassvet.shared.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import com.emelyanov.rassvet.ui.theme.White

@Composable
fun BackButtonLeft(
    modifier: Modifier = Modifier,
    color: Color = RassvetTheme.colors.sectionBackButton,
    onClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null
            ),
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

@ExperimentalFoundationApi
@Preview
@Composable
private fun Preview() {
    RassvetTheme {
        Box(
            Modifier.fillMaxSize()
        ) {
            BackButtonLeft(
                color = White
            ) {

            }
        }
    }
}
