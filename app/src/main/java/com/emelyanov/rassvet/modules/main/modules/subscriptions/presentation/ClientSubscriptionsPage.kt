package com.emelyanov.rassvet.modules.main.modules.subscriptions.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.models.ClientSubscriptionsListViewState
import com.emelyanov.rassvet.modules.main.presentation.components.NAV_BAR_HEIGHT
import com.emelyanov.rassvet.modules.main.presentation.components.NAV_BAR_PADDING
import com.emelyanov.rassvet.shared.presentation.components.NoSubscriptionsScreen
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlin.math.roundToInt

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun ClientSubscriptionsPage(
    clientSubscriptionsListViewState: ClientSubscriptionsListViewState,
    onRefresh: () -> Unit
) {
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = clientSubscriptionsListViewState is ClientSubscriptionsListViewState.Loading
    )

    val dialogVisibility = remember { MutableTransitionState(false) }

    SwipeRefresh(
        modifier = Modifier.fillMaxSize(),
        state = swipeRefreshState,
        onRefresh = onRefresh
    ) {
        when(clientSubscriptionsListViewState) {
            is ClientSubscriptionsListViewState.Loading -> { }

            is ClientSubscriptionsListViewState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(
                            state = rememberScrollState()
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = clientSubscriptionsListViewState.message,
                        style = RassvetTheme.typography.cardBody1
                            .copy(color = RassvetTheme.colors.error)
                    )
                }
            }

            is ClientSubscriptionsListViewState.OneSubscription -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(
                            state = rememberScrollState()
                        )
                ) {
                    OneSubscriptionPage(clientSubscriptionsListViewState.id)
                }
            }

            is ClientSubscriptionsListViewState.SeveralSubscriptions -> {
                SeveralSubscriptionsPage(
                    subs = clientSubscriptionsListViewState.ids,
                    onSubClick = {
                        dialogVisibility.targetState = true
                    }
                )
            }

            is ClientSubscriptionsListViewState.NoSubscriptions -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(
                            state = rememberScrollState()
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    NoSubscriptionsScreen(
                        modifier = Modifier.fillMaxSize(),
                        onSubscriptionsClick = {}
                    )
                }
            }
        }
    }

    AnimatedVisibility(
        visibleState = dialogVisibility,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Dialog(
            onDismissRequest = { dialogVisibility.targetState = false },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = { dialogVisibility.targetState = false }
                    ),
                contentAlignment = Alignment.Center,
            ) {
                val offset = with(LocalDensity.current) { maxHeight.toPx().roundToInt()/2 }

                Column(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .animateEnterExit(
                            enter = slideInVertically(
                                initialOffsetY = { _ ->
                                    offset
                                }
                            ),
                            exit = slideOutVertically(
                                targetOffsetY = { _ ->
                                    offset * 2
                                }
                            )
                        ),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    SubscriptionDetailCard()

                    Text(
                        text = "Дата оформления: 10.12.2021",
                        style = RassvetTheme.typography.cardBody2
                            .copy(color = RassvetTheme.colors.logoColor)
                    )

                    Text(
                        text = "Действительна до 10.12.2022",
                        style = RassvetTheme.typography.cardBody2
                            .copy(color = RassvetTheme.colors.logoColor)
                    )
                }
            }
        }
    }
}

@Composable
private fun OneSubscriptionPage(
    id: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        SubscriptionDetailCard()

        Text(
            text = "Дата оформления: 10.12.2021",
            style = RassvetTheme.typography.cardBody2
                .copy(color = RassvetTheme.colors.surfaceText)
        )

        Text(
            text = "Действительна до 10.12.2022",
            style = RassvetTheme.typography.cardBody2
                .copy(color = RassvetTheme.colors.surfaceText)
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun SeveralSubscriptionsPage(
    subs: List<Int>,
    onSubClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        cells = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(start = 15.dp, top = 15.dp, end = 15.dp, bottom = (NAV_BAR_HEIGHT + NAV_BAR_PADDING + 15).dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(subs) { card ->
            key(card){
                SubscriptionShortCard(
                    onClick = {
                        onSubClick(card)
                    }
                )
            }
        }
    }
}