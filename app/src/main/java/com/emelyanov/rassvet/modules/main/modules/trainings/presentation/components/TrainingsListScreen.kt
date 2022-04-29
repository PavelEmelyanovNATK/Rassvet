package com.emelyanov.rassvet.modules.main.modules.trainings.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.modules.main.modules.trainings.domain.models.TrainingsListViewState
import com.emelyanov.rassvet.modules.main.presentation.components.NAV_BAR_HEIGHT
import com.emelyanov.rassvet.modules.main.presentation.components.NAV_BAR_PADDING
import com.emelyanov.rassvet.shared.presentation.components.NoSubscriptionsScreen
import com.emelyanov.rassvet.shared.presentation.components.SolidBackgroundBox
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.iamjosephmj.flinger.bahaviours.StockFlingBehaviours

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun TrainingsListScreen(
    trainingsListViewState: TrainingsListViewState,
    onRefresh: () -> Unit
) {
    val pagerState = rememberPagerState(0)

    SolidBackgroundBox {
        BoxWithConstraints {
            val pxHeight = with(LocalDensity.current) { maxHeight.toPx() }
            val pxWidth = with(LocalDensity.current) { maxWidth.toPx() }

            val swipeRefreshState = rememberSwipeRefreshState(trainingsListViewState is TrainingsListViewState.Loading)

            val toolbarExpanderState = rememberDropdownBarState(initialValue = true)

            val pagerOffset = animateDpAsState(
                targetValue =
                if(toolbarExpanderState.currentValue)
                    TOOLBAR_EXPANDED_HEIGHT.dp
                else
                    TOOLBAR_COLLAPSED_HEIGHT.dp,
                animationSpec = spring()
            )

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                SwipeRefresh(
                    modifier = Modifier
                        .fillMaxSize(),
                    state = swipeRefreshState,
                    onRefresh = onRefresh,
                    indicatorPadding = PaddingValues(top = pagerOffset.value)
                ) {
                    when(trainingsListViewState) {
                        is TrainingsListViewState.Loading -> {

                        }
                        is TrainingsListViewState.NoSubscriptions -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .verticalScroll(rememberScrollState()),
                                contentAlignment = Alignment.Center
                            ) {
                                NoSubscriptionsScreen()
                            }
                        }
                        is TrainingsListViewState.PresentInfo -> {
                            HorizontalPager(
                                modifier = Modifier
                                    .fillMaxSize(),
                                count = 2,
                                state = pagerState
                            ) { page ->
                                when(page) {
                                    0 -> ActiveTrainingsPage(
                                        topOffset = pagerOffset.value,
                                        activeTrainings = trainingsListViewState.activeTrainings,
                                        onTrainingClick = trainingsListViewState.onTrainingClick
                                    )
                                    1 -> PastTrainingsPage(
                                        topOffset = pagerOffset.value,
                                        pastTrainings = trainingsListViewState.pastTrainings,
                                        onTrainingClick = trainingsListViewState.onTrainingClick
                                    )
                                }
                            }
                        }
                    }
                }

                TrainingsDropdownBar(
                    pagerState = pagerState,
                    barState = toolbarExpanderState,
                    trainingsListViewState = trainingsListViewState,
                    gradientOffsetY = pxHeight,
                    gradientOffsetX = pxWidth
                )
            }
        }
    }
}

@Composable
fun ActiveTrainingsPage(
    topOffset: Dp = 0.dp,
    activeTrainings: Map<Int, List<Int>>,
    onTrainingClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 15.dp, top = 15.dp, end = 15.dp, bottom = (NAV_BAR_HEIGHT + NAV_BAR_PADDING + 15).dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        flingBehavior = StockFlingBehaviours.smoothScroll()
    ) {
        item {
            Spacer(Modifier.height(topOffset))
        }

        activeTrainings.forEach { group ->
            item {
                key(group.key) {
                    TrainingGroup() {
                        group.value.forEach {
                            TrainingGroupItem(
                                onClick = { onTrainingClick(it) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PastTrainingsPage(
    topOffset: Dp = 0.dp,
    pastTrainings: List<Int>,
    onTrainingClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 15.dp, top = 15.dp, end = 15.dp, bottom = (NAV_BAR_HEIGHT + NAV_BAR_PADDING + 15).dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        flingBehavior = StockFlingBehaviours.smoothScroll()
    ) {
        item {
            Spacer(Modifier.height(topOffset))
        }

        pastTrainings.forEach { training ->
            item {
                key(training) {
                    TrainingShortCard(
                        onClick = { onTrainingClick(training) }
                    )
                }
            }
        }
    }
}