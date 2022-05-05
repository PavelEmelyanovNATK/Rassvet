package com.emelyanov.rassvet.modules.main.modules.trainings.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import com.emelyanov.rassvet.modules.main.modules.trainings.domain.models.TrainingDetailsViewState
import com.emelyanov.rassvet.shared.domain.utils.formatToBeautifulDateTimeString
import com.emelyanov.rassvet.shared.domain.utils.getTimeStringFromMinutest
import com.emelyanov.rassvet.shared.presentation.components.BackButtonLeft
import com.emelyanov.rassvet.shared.presentation.components.ErrorView
import com.emelyanov.rassvet.shared.presentation.components.ShimmerBox
import com.emelyanov.rassvet.shared.presentation.components.SolidBackgroundBox
import com.emelyanov.rassvet.ui.theme.Gray
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import io.iamjosephmj.flinger.bahaviours.StockFlingBehaviours

@Composable
fun TrainingDetailsScreen(
    trainingDetailsViewState: TrainingDetailsViewState,
    onBackClick: () -> Unit
) {

    val scrollState = rememberScrollState()

    SolidBackgroundBox {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(minHeight = 120.dp),
                shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp),
                color = RassvetTheme.colors.surfaceBackground,
                elevation = 1.dp
            ) {
                when(trainingDetailsViewState) {
                    is TrainingDetailsViewState.Loading -> HeaderLoading(onBackClick)
                    is TrainingDetailsViewState.PresentInfo -> Header(
                        viewState = trainingDetailsViewState,
                        onBackClick = onBackClick
                    )
                    is TrainingDetailsViewState.Error -> ErrorView(
                        modifier = Modifier.fillMaxWidth(),
                        message = trainingDetailsViewState.message
                    )
                }
            }

            if(trainingDetailsViewState is TrainingDetailsViewState.PresentInfo)
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

                    Text(
                        text = trainingDetailsViewState.description,
                        style = RassvetTheme.typography.cardBody1
                            .copy(color = RassvetTheme.colors.surfaceText)
                    )

                    Spacer(Modifier.height(25.dp))

                    Text(
                        text = "Тренер",
                        style = RassvetTheme.typography.cardGroupTitle
                            .copy(color = RassvetTheme.colors.surfaceText)
                    )

                    Spacer(Modifier.height(6.dp))

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
                            text = trainingDetailsViewState.trainerFullName,
                            style = RassvetTheme.typography.cardBody1
                                .copy(color = RassvetTheme.colors.surfaceText)
                        )
                    }

                    Spacer(Modifier.height(15.dp))
                }
            else
                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
        }
    }
}

private const val CR = 8

@Composable
private fun HeaderLoading(
    onBackClick: () -> Unit
) {

    Column(
        modifier = Modifier.padding(15.dp)
    ) {
        BackButtonLeft(
            color = RassvetTheme.colors.sectionBackButton,
            onClick = onBackClick
        )

        Spacer(Modifier.height(15.dp))

        ShimmerBox(
            modifier = Modifier.clip(RoundedCornerShape(CR.dp))
        ) {
            Text(
                modifier = it,
                text = "Заголовок",
                style = RassvetTheme.typography.toolbarTitle
                    .copy(color = RassvetTheme.colors.surfaceText)
            )
        }

        Spacer(Modifier.height(6.dp))

        ShimmerBox(
            modifier = Modifier.clip(RoundedCornerShape(CR.dp))
        ) {
            Text(
                modifier = it,
                text = "Секция: тренажёрный",
                style = RassvetTheme.typography.cardBody2
                    .copy(color = RassvetTheme.colors.surfaceText)
            )
        }

        Spacer(Modifier.height(3.dp))

        ShimmerBox(
            modifier = Modifier.clip(RoundedCornerShape(CR.dp))
        ) {
            Text(
                modifier = it,
                text = "Зал: тренажёрный зал №2Б",
                style = RassvetTheme.typography.cardBody2
                    .copy(color = RassvetTheme.colors.surfaceText)
            )
        }


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

                ShimmerBox(
                    modifier = Modifier.clip(RoundedCornerShape(CR.dp))
                ) {
                    Text(
                        modifier = it,
                        text = "1ч 30м",
                        style = RassvetTheme.typography.cardBody2
                            .copy(color = RassvetTheme.colors.cardIcons)
                    )
                }
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

                ShimmerBox(
                    modifier = Modifier.clip(RoundedCornerShape(CR.dp))
                ) {
                    Text(
                        modifier = it,
                        text = "10 янв 8:00",
                        style = RassvetTheme.typography.cardBody2
                            .copy(color = RassvetTheme.colors.surfaceText)
                    )
                }
            }
        }
    }

}

@Composable
private fun Header(
    viewState: TrainingDetailsViewState.PresentInfo,
    onBackClick: () -> Unit
) {

    Column(
        modifier = Modifier.padding(15.dp)
    ) {
        BackButtonLeft(
            color = RassvetTheme.colors.sectionBackButton,
            onClick = onBackClick
        )

        Spacer(Modifier.height(15.dp))

        Text(
            text = viewState.title,
            style = RassvetTheme.typography.toolbarTitle
                .copy(color = RassvetTheme.colors.surfaceText)
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = "Секция: ${viewState.section}",
            style = RassvetTheme.typography.cardBody2
                .copy(color = RassvetTheme.colors.surfaceText)
        )

        Spacer(Modifier.height(3.dp))

        Text(
            text = "Зал: ${viewState.room}",
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
                    text = getTimeStringFromMinutest(viewState.durationInMinutes),
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
                    text = viewState.startDate.formatToBeautifulDateTimeString(),
                    style = RassvetTheme.typography.cardBody2
                        .copy(color = RassvetTheme.colors.surfaceText)
                )
            }
        }
    }
}



@ExperimentalFoundationApi
@Preview
@Composable
private fun Preview() {
    RassvetTheme {
        //TrainingDetailsScreen({})
    }
}