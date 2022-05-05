package com.emelyanov.rassvet.modules.firstboot.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.shared.presentation.components.GlassButton
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import kotlinx.coroutines.launch

@Composable
fun FirstBootInfoPage(
    onNextClick: () -> Unit = {}
){
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            modifier = Modifier
                //.size(
                //    (logoCurrentWidth + additionalLogoWidth).dp,
                //    (logoCurrentHeight + additionalLogoHeight).dp
                //)
                .size(LOGO_DEFAULT_WIDTH.dp, LOGO_DEFAULT_HEIGHT.dp)
                .align(Alignment.Center)
                //.offset(x = logoCurrentOffsetX.dp, y = logoCurrentOffsetY.dp)
                //.offset(x = additionalLogoOffsetX.dp, y = additionalLogoOffsetY.dp)
                .offset(y = LOGO_DEFAULT_OFFSET_Y.dp),
            painter = painterResource(id = R.drawable.ic_rassvet_logo),
            contentDescription = "Logo",
            tint = RassvetTheme.colors.logoColor
        )

        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = LOGO_TITLE_OFFSET.dp),
            text = "Рассвет",
            style = RassvetTheme.typography.logoText
                .copy(color = RassvetTheme.colors.logoColor)
        )

        Text(
            modifier = Modifier
                .size(QUOTE_WIDTH.dp, QUOTE_HEIGHT.dp)
                .align(Alignment.Center),
            text = "Крупнейший\nспортивный комплекс\nНовосибирска",
            style = RassvetTheme.typography.quoteText
                .copy(
                    color = RassvetTheme.colors.logoColor,
                    textAlign = TextAlign.Center
                )
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GlassButton(
                text = "Далее",
                onClick = onNextClick
            )

            Spacer(
                modifier = Modifier
                    .fillMaxHeight(0.185f)
                    .sizeIn(maxHeight = 135.dp)
            )
        }
    }
}