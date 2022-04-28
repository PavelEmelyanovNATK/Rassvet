package com.emelyanov.rassvet.modules.main.modules.trainings.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.modules.main.modules.trainings.domain.models.TrainingsListViewState
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
    barState: SwipeableState<Boolean>,
    trainingsListViewState: TrainingsListViewState,
    gradientOffsetX: Float = Float.POSITIVE_INFINITY,
    gradientOffsetY: Float = Float.POSITIVE_INFINITY
) {
    val coroutineScope = rememberCoroutineScope()

    val expandedHeightPx =  with(LocalDensity.current){ TOOLBAR_EXPANDED_HEIGHT.dp.toPx() }
    val collapsedHeightPx = with(LocalDensity.current){ TOOLBAR_COLLAPSED_HEIGHT.dp.toPx() }
    val actualHeight = with(LocalDensity.current) { barState.offset.value.toDp() }

    var lastExpanderState by remember { mutableStateOf(true) }

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

    LaunchedEffect(key1 = trainingsListViewState) {
        if(trainingsListViewState is TrainingsListViewState.PresentInfo) {
            if(lastExpanderState == true && barState.currentValue == false)
                    barState.animateTo(true)
        }
        else {
            lastExpanderState = barState.currentValue
            barState.animateTo(false)
        }
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
        when (trainingsListViewState) {
            is TrainingsListViewState.PresentInfo -> {
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
                                .background(
                                    RassvetTheme.colors.logoColor
                                        .copy(alpha = firstPageLineAlpha)
                                )
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
                                .background(
                                    RassvetTheme.colors.logoColor
                                        .copy(alpha = secondPageLineAlpha)
                                )
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
                        .height(18.dp)
                        .swipeable(
                            state = barState,
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
                            .padding(top = 6.dp)
                    )
                }
            }
            is TrainingsListViewState.Loading -> {
                val stickTransition = rememberInfiniteTransition()
                val stickAlpha = stickTransition.animateFloat(
                    initialValue = 0.3f,
                    targetValue = 0.8f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = 1200),
                        repeatMode = RepeatMode.Reverse
                    )
                )

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
                            .height(18.dp)
                            .swipeable(
                                state = barState,
                                anchors = mapOf(
                                    expandedHeightPx to true,
                                    collapsedHeightPx to false
                                ),
                                orientation = Orientation.Vertical,
                                enabled = false
                            )
                    ) {
                        Box(
                            Modifier
                                .clip(RoundedCornerShape(50))
                                .size(120.dp, 4.dp)
                                .background(RassvetTheme.colors.logoColor.copy(alpha = stickAlpha.value))
                                .align(Alignment.TopCenter)
                                .padding(top = 6.dp)
                        )
                    }
                }
            }
            else -> {
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
                            .height(16.dp)
                            .swipeable(
                                state = barState,
                                anchors = mapOf(
                                    expandedHeightPx to true,
                                    collapsedHeightPx to false
                                ),
                                orientation = Orientation.Vertical,
                                enabled = false
                            )
                    ) {
                        Box(
                            Modifier
                                .clip(RoundedCornerShape(50))
                                .size(120.dp, 4.dp)
                                .background(RassvetTheme.colors.logoColor.copy(alpha = 0.3f))
                                .align(Alignment.TopCenter)
                                .padding(top = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun rememberDropdownBarState(initialValue: Boolean)
    = rememberSwipeableState(initialValue = initialValue)