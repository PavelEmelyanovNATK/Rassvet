package com.emelyanov.rassvet.modules.main.modules.trainings.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.shared.domain.utils.formatToBeautifulDateTimeString
import com.emelyanov.rassvet.shared.domain.utils.getTimeStringFromMinutest
import com.emelyanov.rassvet.ui.theme.Gray
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import java.util.*

@Composable
fun TrainingGroup(
    modifier:Modifier = Modifier,
    title: String,
    content: @Composable TrainingGroupScope.() -> Unit
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        color = RassvetTheme.colors.surfaceBackground,
        elevation = 2.dp
    ) {
        Column {
            Text(
                modifier = Modifier.padding(start = 15.dp, top = 15.dp, end = 15.dp, bottom = 15.dp),
                text = title,
                style = RassvetTheme.typography.cardGroupTitle
                    .copy(color = RassvetTheme.colors.surfaceText)
            )

            TrainingGroupScopeImpl.content()
        }
    }
}

@Composable
fun TrainingShortCard(
    modifier: Modifier = Modifier,
    title: String,
    durationInMinutes: Int,
    trainerFullName: String,
    startDate: Date,
    onClick: () -> Unit
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
                .clickable(
                    onClick = onClick
                )
                .padding(15.dp)
        ) {
            Text(
                //modifier = Modifier.padding(start = 20.dp, top = 5.dp, end = 20.dp),
                text = title,
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
                    text = getTimeStringFromMinutest(durationInMinutes),
                    style = RassvetTheme.typography.cardBody2
                        .copy(color = RassvetTheme.colors.cardIcons)
                )
            }

            Spacer(Modifier.height(2.dp))

            Text(
                text = "Тренер: $trainerFullName",
                style = RassvetTheme.typography.cardBody2
                    .copy(color = RassvetTheme.colors.surfaceText)
            )

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
                    text = startDate.formatToBeautifulDateTimeString(),
                    style = RassvetTheme.typography.cardBody2
                        .copy(color = RassvetTheme.colors.surfaceText)
                )
            }
        }
    }
}

private const val topDividerId = "topDivider"
private const val titleId = "title"
private const val timerId = "timerIcon"
private const val durationId = "duration"
private const val trainerId = "trainer"
private const val calendarId = "calendarIcon"
private const val dateId = "date"

interface TrainingGroupScope {
    @Composable
    fun TrainingGroupItem(
        title: String,
        durationInMinutes: Int,
        trainerFullName: String,
        startDate: Date,
        onClick: () -> Unit
    ) {
        Column(
            modifier = Modifier.clickable(
                onClick = onClick
            )
        ) {
            Divider(
                modifier = Modifier.padding(horizontal = 10.dp),
                color = Gray.copy(alpha = 0.5f),
                thickness = 0.5.dp
            )
            Text(
                modifier = Modifier.padding(start = 20.dp, top = 5.dp, end = 20.dp),
                text = title,
                style = RassvetTheme.typography.cardTitle
                    .copy(color = RassvetTheme.colors.surfaceText)
            )

            Spacer(Modifier.height(3.dp))

            Row(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_timer),
                    contentDescription = "Timer icon",
                    tint = RassvetTheme.colors.cardIcons
                )

                Spacer(Modifier.width(10.dp))

                Text(
                    text = getTimeStringFromMinutest(durationInMinutes),
                    style = RassvetTheme.typography.cardBody2
                        .copy(color = RassvetTheme.colors.cardIcons)
                )
            }

            Spacer(Modifier.height(3.dp))

            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "Тренер: $trainerFullName",
                style = RassvetTheme.typography.cardBody2
                    .copy(color = RassvetTheme.colors.surfaceText)
            )

            Spacer(Modifier.height(3.dp))

            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = "Calendar icon",
                    tint = RassvetTheme.colors.surfaceText
                )

                Spacer(Modifier.width(10.dp))

                Text(
                    text = startDate.formatToBeautifulDateTimeString(),
                    style = RassvetTheme.typography.cardBody2
                        .copy(color = RassvetTheme.colors.surfaceText)
                )
            }

            Spacer(Modifier.height(20.dp))
        }
    }

    @Composable
    fun TrainingGroupItemConstraint(
        title: String,
        durationInMinutes: Int,
        trainerFullName: String,
        startDate: Date,
        onClick: () -> Unit
    ) {
        val constraints = ConstraintSet {
            val topDividerCon = createRefFor(topDividerId)
            val titleCon = createRefFor(titleId)
            val timerCon = createRefFor(timerId)
            val durationCon = createRefFor(durationId)
            val trainerCon = createRefFor(trainerId)
            val calendarCon = createRefFor(calendarId)
            val dateCon = createRefFor(dateId)

            constrain(topDividerCon) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }

            constrain(titleCon) {
                start.linkTo(parent.start)
                top.linkTo(topDividerCon.bottom)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }

            constrain(timerCon) {
                start.linkTo(titleCon.start)
                top.linkTo(titleCon.bottom)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }

            constrain(durationCon) {
                start.linkTo(timerCon.end)
                top.linkTo(timerCon.top)
                bottom.linkTo(timerCon.bottom)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }

            constrain(trainerCon) {
                start.linkTo(titleCon.start)
                top.linkTo(timerCon.bottom)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }

            constrain(dateCon) {
                end.linkTo(titleCon.end)
                top.linkTo(calendarCon.top)
                bottom.linkTo(calendarCon.bottom)
                width = Dimension.wrapContent
            }

            constrain(calendarCon) {
                end.linkTo(dateCon.start)
                top.linkTo(trainerCon.bottom)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
        }

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = onClick
                ),
            constraintSet = constraints
        ) {
            Divider(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .layoutId(topDividerId),
                color = Gray.copy(alpha = 0.5f),
                thickness = 0.5.dp
            )

            Text(
                modifier = Modifier
                    .padding(start = 20.dp, top = 5.dp, end = 20.dp)
                    .layoutId(titleId),
                text = title,
                style = RassvetTheme.typography.cardTitle
                    .copy(color = RassvetTheme.colors.surfaceText)
            )

            Icon(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .layoutId(timerId),
                painter = painterResource(id = R.drawable.ic_timer),
                contentDescription = "Timer icon",
                tint = RassvetTheme.colors.cardIcons
            )

            Text(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .layoutId(durationId),
                text = getTimeStringFromMinutest(durationInMinutes),
                style = RassvetTheme.typography.cardBody2
                    .copy(color = RassvetTheme.colors.cardIcons)
            )

            Text(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .padding(vertical = 3.dp)
                    .layoutId(trainerId),
                text = "Тренер: $trainerFullName",
                style = RassvetTheme.typography.cardBody2
                    .copy(color = RassvetTheme.colors.surfaceText)
            )

            Icon(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .layoutId(calendarId),
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = "Calendar icon",
                tint = RassvetTheme.colors.surfaceText
            )

            Text(
                modifier = Modifier
                    .padding(start = 10.dp, end = 20.dp, bottom = 20.dp)
                    .layoutId(dateId),
                text = startDate.formatToBeautifulDateTimeString(),
                style = RassvetTheme.typography.cardBody2
                    .copy(color = RassvetTheme.colors.surfaceText)
            )
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
                //TrainingShortCard()
                //TrainingShortCard()
                //TrainingShortCard()
                //TrainingShortCard()
            }
        }
    }
}
