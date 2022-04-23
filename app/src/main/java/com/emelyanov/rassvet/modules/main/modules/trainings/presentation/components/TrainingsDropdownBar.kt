package com.emelyanov.rassvet.modules.main.modules.trainings.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch
import kotlin.math.abs


const val TOOLBAR_EXPANDED_HEIGHT = 135f
const val TOOLBAR_COLLAPSED_HEIGHT = 60f
private const val OVER_DRAG_AMOUNT = 30f
private const val TEXT_HEIGHT = 20f

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun TrainingsDropdownBar(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    gradientOffsetX: Float = Float.POSITIVE_INFINITY,
    gradientOffsetY: Float = Float.POSITIVE_INFINITY,
    onStateChanged: (Boolean) -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()

    val swipeState = rememberSwipeableState(initialValue = true)
    val expandedHeightPx =  with(LocalDensity.current){ TOOLBAR_EXPANDED_HEIGHT.dp.toPx() }
    val collapsedHeightPx = with(LocalDensity.current){ TOOLBAR_COLLAPSED_HEIGHT.dp.toPx() }
    val actualHeight = with(LocalDensity.current) { swipeState.offset.value.toDp() }

    val firstPageLineAlpha =
        if(pagerState.currentPage == 0)
            1f - abs(pagerState.currentPageOffset)
        else
            abs(pagerState.currentPageOffset)

    val secondPageLineAlpha =
        if(pagerState.currentPage == 1)
            1f - abs(pagerState.currentPageOffset)
        else
            abs(pagerState.currentPageOffset)

    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(swipeState.currentValue) {
        onStateChanged(swipeState.currentValue)
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    RassvetTheme.colors.toolbarBackground,
                    start = Offset(0f, gradientOffsetY),
                    end = Offset(gradientOffsetX, 0f)
                )
            )
            .height(actualHeight),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier.padding(bottom =  100.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .width(IntrinsicSize.Min)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(0)
                            }
                        }
                    )
            ) {
                Text(
                    text = "Предстоящие",
                    style = RassvetTheme.typography.cardBody2
                        .copy(color = RassvetTheme.colors.logoColor)
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .fillMaxWidth()
                        .height(2.5.dp)
                        .background(RassvetTheme.colors.logoColor
                            .copy(alpha = firstPageLineAlpha))
                )
            }

            Column(
                modifier = Modifier
                    .width(IntrinsicSize.Min)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(1)
                            }
                        }
                    )
            ) {
                Text(
                    text = "Прошедшие",
                    style = RassvetTheme.typography.cardBody2
                        .copy(color = RassvetTheme.colors.logoColor)
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .fillMaxWidth()
                        .height(2.5.dp)
                        .background(RassvetTheme.colors.logoColor
                            .copy(alpha = secondPageLineAlpha))
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(bottom = 60.dp, start = 25.dp)
                .align(Alignment.BottomStart)
        ) {
            Text(
                text = "Секция: ",
                style = RassvetTheme.typography.cardBody2
                    .copy(color = RassvetTheme.colors.logoColor)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Любая",
                    style = RassvetTheme.typography.cardBody2
                        .copy(color = RassvetTheme.colors.logoColor)
                )

                Spacer(Modifier.width(5.dp))

                Icon(
                    painter = painterResource(id = R.drawable.ic_expander),
                    contentDescription = "Expander icon",
                    tint = RassvetTheme.colors.logoColor
                )
            }
        }

        Text(
            modifier = Modifier.padding(bottom = 21.dp),
            text = "Тренировки",
            style = RassvetTheme.typography.toolbarTitle
                .copy(color = RassvetTheme.colors.logoColor)
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(14.dp)
                .swipeable(
                    state = swipeState,
                    anchors = mapOf(
                        expandedHeightPx to true,
                        collapsedHeightPx to false
                    ),
                    orientation = Orientation.Vertical
                )
        ) {
            Box(
                Modifier
                    .clip(RoundedCornerShape(50))
                    .size(120.dp, 4.dp)
                    .background(RassvetTheme.colors.logoColor)
                    .align(Alignment.TopCenter)
                    .padding(top = 2.dp)
            )
        }
    }
}