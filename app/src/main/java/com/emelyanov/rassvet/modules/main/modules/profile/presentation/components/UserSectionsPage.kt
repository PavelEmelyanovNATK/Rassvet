package com.emelyanov.rassvet.modules.main.modules.profile.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.modules.main.presentation.components.NAV_BAR_HEIGHT
import com.emelyanov.rassvet.modules.main.presentation.components.NAV_BAR_PADDING
import com.emelyanov.rassvet.shared.presentation.components.BackButtonLeft
import com.emelyanov.rassvet.shared.presentation.components.SolidBackgroundBox
import com.emelyanov.rassvet.ui.theme.Gray
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import com.emelyanov.rassvet.ui.theme.Red
import io.iamjosephmj.flinger.bahaviours.StockFlingBehaviours

@Composable
fun UserSectionsPage() {
    SolidBackgroundBox {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp),
                color = RassvetTheme.colors.surfaceBackground,
                elevation = 1.dp
            ) {
                Column(
                    modifier = Modifier.padding(15.dp)
                ) {
                    BackButtonLeft(
                        color = RassvetTheme.colors.sectionBackButton
                    )

                    Spacer(Modifier.height(15.dp))

                    Text(
                        text = "Заголовок",
                        style = RassvetTheme.typography.toolbarTitle
                            .copy(color = RassvetTheme.colors.surfaceText)
                    )
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
                Spacer(Modifier.height(15.dp))

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
                                    onClick = {}
                                )
                                .padding(vertical = 10.dp, horizontal = 15.dp),
                            text = "Секция",
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
                                    onClick = {}
                                )
                                .padding(vertical = 10.dp, horizontal = 15.dp),
                            text = "Секция",
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
                                    onClick = {}
                                )
                                .padding(vertical = 10.dp, horizontal = 15.dp),
                            text = "Секция",
                            style = RassvetTheme.typography.cardBody1
                                .copy(color = RassvetTheme.colors.surfaceText)
                        )
                    }
                }

                Spacer(Modifier.height((NAV_BAR_HEIGHT + NAV_BAR_PADDING + 15).dp))
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
private fun Preview() {
    RassvetTheme {
        UserSectionsPage()
    }
}