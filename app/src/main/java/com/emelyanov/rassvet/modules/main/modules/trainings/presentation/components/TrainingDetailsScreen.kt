package com.emelyanov.rassvet.modules.main.modules.trainings.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.emelyanov.rassvet.shared.presentation.components.BackButtonLeft
import com.emelyanov.rassvet.shared.presentation.components.SolidBackgroundBox
import com.emelyanov.rassvet.ui.theme.Gray
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import io.iamjosephmj.flinger.bahaviours.StockFlingBehaviours

@Composable
fun TrainingDetailsScreen(
    onBackPressed: () -> Unit
) {

    val scrollState = rememberScrollState()

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
                        color = RassvetTheme.colors.sectionBackButton,
                        onClick = onBackPressed
                    )

                    Spacer(Modifier.height(15.dp))

                    Text(
                        text = "Заголовок",
                        style = RassvetTheme.typography.toolbarTitle
                            .copy(color = RassvetTheme.colors.surfaceText)
                    )

                    Text(
                        text = "Секция: тренажёрный зал",
                        style = RassvetTheme.typography.cardBody2
                            .copy(color = RassvetTheme.colors.surfaceText)
                    )

                    Text(
                        text = "Зал: тренажёрный зал №2Б",
                        style = RassvetTheme.typography.cardBody2
                            .copy(color = RassvetTheme.colors.surfaceText)
                    )

                    Spacer(Modifier.height(25.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.align(Alignment.CenterStart),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_timer),
                                contentDescription = "Timer icon",
                                tint = RassvetTheme.colors.cardIcons
                            )

                            Spacer(Modifier.width(10.dp))

                            Text(
                                text = "1ч 30м",
                                style = RassvetTheme.typography.cardBody2
                                    .copy(color = RassvetTheme.colors.cardIcons)
                            )
                        }

                        Row(
                            modifier = Modifier.align(Alignment.CenterEnd),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_calendar),
                                contentDescription = "Calendar icon",
                                tint = RassvetTheme.colors.surfaceText
                            )

                            Spacer(Modifier.width(10.dp))

                            Text(
                                text = "10 янв 8:00",
                                style = RassvetTheme.typography.cardBody2
                                    .copy(color = RassvetTheme.colors.surfaceText)
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(
                        state = scrollState,
                        flingBehavior = StockFlingBehaviours.smoothScroll()
                    )
                    .padding(horizontal = 15.dp),
            ) {
                Spacer(Modifier.height(25.dp))

                Text(
                    text = "Описание",
                    style = RassvetTheme.typography.cardGroupTitle
                        .copy(color = RassvetTheme.colors.surfaceText)
                )

                (0..20).forEach {
                    Text(
                        text = "Описание",
                        style = RassvetTheme.typography.cardBody1
                            .copy(color = RassvetTheme.colors.surfaceText)
                    )
                }

                Spacer(Modifier.height(25.dp))

                Text(
                    text = "Тренер",
                    style = RassvetTheme.typography.cardGroupTitle
                        .copy(color = RassvetTheme.colors.surfaceText)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        Modifier
                            .size(45.dp)
                            .clip(CircleShape)
                            .background(Gray)
                    )

                    Spacer(Modifier.width(15.dp))

                    Text(
                        text = "Тиньков Олег Олегович",
                        style = RassvetTheme.typography.cardBody1
                            .copy(color = RassvetTheme.colors.surfaceText)
                    )
                }

                Spacer(Modifier.height(15.dp))
            }
        }
    }
}


@ExperimentalFoundationApi
@Preview
@Composable
private fun Preview() {
    RassvetTheme {
        TrainingDetailsScreen({})
    }
}