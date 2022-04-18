package com.emelyanov.rassvet.shared.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import com.emelyanov.rassvet.ui.theme.White

@Composable
fun LogoWithTitle(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(185.dp, 120.dp),
            painter = painterResource(id = R.drawable.ic_rassvet_logo),
            contentDescription = "Logo",
            tint = White
        )

        Text(
            text = "Рассвет",
            style = RassvetTheme.typography.logoText
                .copy(color = RassvetTheme.colors.logoColor)
        )
    }
}
