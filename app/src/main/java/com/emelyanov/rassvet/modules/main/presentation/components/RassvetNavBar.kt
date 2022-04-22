package com.emelyanov.rassvet.modules.main.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import com.emelyanov.rassvet.R

const val NAV_BAR_HEIGHT = 60f
@Composable
fun RassvetNavBar(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            elevation = 1.dp,
            color = RassvetTheme.colors.surfaceBackground
        ) { }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 3.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_dumbel),
                        contentDescription = "Trainings icon",
                        tint = RassvetTheme.colors.navbarUnselectedItem
                    )

                    Text(
                        text = "Тренировки",
                        style = RassvetTheme.typography.navItemCaption
                            .copy(color = RassvetTheme.colors.navbarUnselectedItem)
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .size(74.dp,48.dp)
                            .background(RassvetTheme.colors.navbarUnselectedItem),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(56.dp,36.dp),
                            painter = painterResource(id = R.drawable.ic_rassvet_logo),
                            contentDescription = "Subscriptions button",
                            tint = RassvetTheme.colors.surfaceBackground
                        )
                    }

                    Text(
                        text = "Абонементы",
                        style = RassvetTheme.typography.navItemCaption
                            .copy(color = RassvetTheme.colors.navbarUnselectedItem)
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = "Profile icon",
                        tint = RassvetTheme.colors.navbarUnselectedItem
                    )

                    Text(
                        text = "Профиль",
                        style = RassvetTheme.typography.navItemCaption
                            .copy(color = RassvetTheme.colors.navbarUnselectedItem)
                    )
                }
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
private fun Preview(){
    RassvetTheme{
        Box(
            Modifier
                .fillMaxSize()
                .background(RassvetTheme.colors.layoutBackground)
        ){
            RassvetNavBar()
        }
    }
}
