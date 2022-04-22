package com.emelyanov.rassvet.modules.main.modules.trainings.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.ui.theme.Gray
import com.emelyanov.rassvet.ui.theme.RassvetTheme

@Composable
fun TrainingGroup(
    modifier:Modifier = Modifier,
    content: @Composable TrainingGroupScope.() -> Unit
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        color = RassvetTheme.colors.surfaceBackground,
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                text = "Секция",
                style = RassvetTheme.typography.cardGroupTitle
                    .copy(color = RassvetTheme.colors.surfaceText)
            )

            TrainingGroupScopeImpl.content()
        }
    }
}

@Composable
fun TrainingShortCard(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        color = RassvetTheme.colors.surfaceBackground,
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(
                text = "Заголовок",
                style = RassvetTheme.typography.cardTitle
                    .copy(color = RassvetTheme.colors.surfaceText)
            )

            Spacer(Modifier.height(2.dp))

            Row(
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

            Spacer(Modifier.height(2.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Тренер: ",
                    style = RassvetTheme.typography.cardBody2
                        .copy(color = RassvetTheme.colors.surfaceText)
                )

                Text(
                    text = "Тиньков Олег Олегович",
                    style = RassvetTheme.typography.cardBody2
                        .copy(color = RassvetTheme.colors.surfaceText)
                )
            }

            Spacer(Modifier.height(2.dp))

            Row(
                modifier = Modifier.align(Alignment.End),
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

interface TrainingGroupScope {
    @Composable
    fun TrainingGroupItem(

    ) {
        Column(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 10.dp)
        ) {
            Divider(
                color = Gray.copy(alpha = 0.5f),
                thickness = 0.5.dp
            )
            Text(
                text = "Заголовок",
                style = RassvetTheme.typography.cardTitle
                    .copy(color = RassvetTheme.colors.surfaceText)
            )

            Spacer(Modifier.height(2.dp))

            Row(
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

            Spacer(Modifier.height(2.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Тренер: ",
                    style = RassvetTheme.typography.cardBody2
                        .copy(color = RassvetTheme.colors.surfaceText)
                )

                Text(
                    text = "Тиньков Олег Олегович",
                    style = RassvetTheme.typography.cardBody2
                        .copy(color = RassvetTheme.colors.surfaceText)
                )
            }

            Spacer(Modifier.height(2.dp))

            Row(
                modifier = Modifier.align(Alignment.End),
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

internal object TrainingGroupScopeImpl : TrainingGroupScope


@ExperimentalFoundationApi
@Preview
@Composable
private fun Preview(){
    RassvetTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(RassvetTheme.colors.layoutBackground)){
            //TrainingGroup {
            //    TrainingGroupItem()
            //    TrainingGroupItem()
            //    TrainingGroupItem()
            //}

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                TrainingShortCard()
                TrainingShortCard()
                TrainingShortCard()
                TrainingShortCard()
            }
        }
    }
}
