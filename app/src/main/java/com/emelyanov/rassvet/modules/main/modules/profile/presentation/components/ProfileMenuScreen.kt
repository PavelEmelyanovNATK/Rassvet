package com.emelyanov.rassvet.modules.main.modules.profile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.modules.main.presentation.components.LocalNavBarVisibilityState
import com.emelyanov.rassvet.modules.main.presentation.components.NAV_BAR_HEIGHT
import com.emelyanov.rassvet.modules.main.presentation.components.NAV_BAR_PADDING
import com.emelyanov.rassvet.shared.presentation.components.SolidBackgroundBox
import com.emelyanov.rassvet.ui.theme.Gray
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import com.emelyanov.rassvet.ui.theme.Red

@Composable
fun ProfileMenuScreen(
    onSectionsClick: () -> Unit,
    onAboutClick: () -> Unit,
    onExitClick: () -> Unit
) {
    LocalNavBarVisibilityState.current.value = true

    //val scrollState = rememberScrollState()

    SolidBackgroundBox {
        BoxWithConstraints {
            val pxHeight = with(LocalDensity.current) { maxHeight.toPx() }
            val pxWidth = with(LocalDensity.current) { maxWidth.toPx() }
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
                        .fillMaxWidth()
                        .background(
                            brush = Brush.linearGradient(
                                RassvetTheme.colors.layoutGradientBackground,
                                start = Offset(0f, pxHeight),
                                end = Offset(pxWidth , 0f)
                            )
                        )
                    //.shadow(
                    //    elevation = 1.dp,
                    //    shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
                    //),
                ) {
                    Column(
                        modifier = Modifier.padding(15.dp)
                    ) {

                        Text(
                            modifier = Modifier.sizeIn(maxWidth = 250.dp),
                            text = "Емельянов Павел Александрович",
                            style = RassvetTheme.typography.toolbarTitle
                                .copy(color = RassvetTheme.colors.logoColor)
                        )

                        Spacer(Modifier.height(25.dp))

                        Text(
                            text = "День рождения: 10 фев",
                            style = RassvetTheme.typography.cardBody2
                                .copy(color = RassvetTheme.colors.logoColor)
                        )

                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.CenterStart),
                                text = "Зарегистрирован: ",
                                style = RassvetTheme.typography.cardBody2
                                    .copy(color = RassvetTheme.colors.logoColor)
                            )

                            Row(
                                modifier = Modifier.align(Alignment.CenterEnd),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_calendar),
                                    contentDescription = "Calendar icon",
                                    tint = RassvetTheme.colors.logoColor
                                )

                                Spacer(Modifier.width(10.dp))

                                Text(
                                    text = "10.02.2021",
                                    style = RassvetTheme.typography.cardBody2
                                        .copy(color = RassvetTheme.colors.logoColor)
                                )
                            }
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        //.verticalScroll(
                        //    state = scrollState,
                        //    flingBehavior = StockFlingBehaviours.smoothScroll()
                        //)
                        .padding(horizontal = 15.dp),
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Spacer(Modifier.height(15.dp))

                        Text(
                            modifier = Modifier.padding(horizontal = 15.dp),
                            text = "Прочее",
                            style = RassvetTheme.typography.cardGroupTitle
                                .copy(color = RassvetTheme.colors.surfaceText)
                        )

                        Spacer(Modifier.height(6.dp))

                        Surface(
                            shape = RoundedCornerShape(15.dp),
                            color = RassvetTheme.colors.surfaceBackground,
                            elevation = 1.dp
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable(
                                            onClick = onSectionsClick
                                        )
                                        .padding(vertical = 10.dp, horizontal = 15.dp),
                                    text = "Мои секции",
                                    style = RassvetTheme.typography.cardBody1
                                        .copy(color = RassvetTheme.colors.surfaceText)
                                )

                                Divider(
                                    modifier = Modifier.padding(horizontal = 10.dp),
                                    color = Gray.copy(alpha = 0.5f),
                                    thickness = 0.5.dp
                                )

                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable(
                                            onClick = onAboutClick
                                        )
                                        .padding(vertical = 10.dp, horizontal = 15.dp),
                                    text = "О нас",
                                    style = RassvetTheme.typography.cardBody1
                                        .copy(color = RassvetTheme.colors.surfaceText)
                                )
                            }
                        }

                        Spacer(
                            Modifier
                                .weight(1f)
                        )

                        Surface(
                            shape = RoundedCornerShape(15.dp),
                            color = RassvetTheme.colors.surfaceBackground,
                            elevation = 1.dp
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(
                                        onClick = onExitClick
                                    )
                                    .padding(vertical = 10.dp, horizontal = 15.dp),
                                text = "Выйти",
                                style = RassvetTheme.typography.cardBody1
                                    .copy(color = Red)
                            )
                        }

                        Spacer(Modifier.height((NAV_BAR_HEIGHT + NAV_BAR_PADDING + 15).dp))
                    }
                }
            }
        }
    }
}