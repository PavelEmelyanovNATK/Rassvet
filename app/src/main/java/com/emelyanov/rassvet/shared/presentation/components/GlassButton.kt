package com.emelyanov.rassvet.shared.presentation.components

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.ui.theme.CreamyPurple
import com.emelyanov.rassvet.ui.theme.RassvetTheme



@Composable
fun GlassButton(
    modifier: Modifier = Modifier,
    text: String = "Button",
    onClick: () -> Unit,
    textStyle: TextStyle = RassvetTheme.typography.buttonText
        .copy(RassvetTheme.colors.buttonText)
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(25.dp))
            .background(RassvetTheme.colors.input)
            .clickable(
                onClick = onClick
            )
            .padding(start = 30.dp, top = 6.dp, bottom = 7.dp, end = 30.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = textStyle
        )
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
private fun Preview(){
    RassvetTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(CreamyPurple)
        ) {
            GlassButton(
                onClick = {}
            )
        }
    }
}