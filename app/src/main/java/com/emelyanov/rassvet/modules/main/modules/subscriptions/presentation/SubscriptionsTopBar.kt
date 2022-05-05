package com.emelyanov.rassvet.modules.main.modules.subscriptions.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsListDestinations
import com.emelyanov.rassvet.shared.presentation.components.BackButtonLeft
import com.emelyanov.rassvet.ui.theme.RassvetTheme

@ExperimentalAnimationApi
@Composable
fun SubscriptionsTopBar(
    modifier: Modifier = Modifier,
    subscriptionsListNavController: NavHostController,
    onAddSubClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(TOOL_BAR_HEIGHT.dp)
    ) {
        val curEntry = subscriptionsListNavController.currentBackStackEntryAsState()
        val isBackButtonVisible = remember { MutableTransitionState(curEntry.value?.destination?.route == SubscriptionsListDestinations.Sections.route) }
        val isAddSubButtonVisible = remember { MutableTransitionState(curEntry.value?.destination?.route == SubscriptionsListDestinations.ClientSubscriptions.route) }
        val currentTitleText = remember { mutableStateOf("Абонементы") }

        isBackButtonVisible.targetState = curEntry.value?.destination?.route == SubscriptionsListDestinations.Sections.route
        isAddSubButtonVisible.targetState = curEntry.value?.destination?.route == SubscriptionsListDestinations.ClientSubscriptions.route

        currentTitleText.value = when(curEntry.value?.destination?.route) {
            SubscriptionsListDestinations.Sections.route -> "Секции"
            SubscriptionsListDestinations.ClientSubscriptions.route -> "Абонементы"
            else -> ""
        }

        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 25.dp),
            visibleState = isBackButtonVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            BackButtonLeft(
                color = RassvetTheme.colors.logoColor,
                onClick = onBackClick
            )
        }

        AnimatedContent(
            modifier = Modifier.align(Alignment.Center),
            targetState = currentTitleText,
            transitionSpec = { fadeIn() with  fadeOut() }
        ) { text ->
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = text.value,
                style = RassvetTheme.typography.toolbarTitle
                    .copy(color = RassvetTheme.colors.logoColor)
            )
        }

        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 25.dp),
            visibleState = isAddSubButtonVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            IconButton(
                onClick = onAddSubClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_sub),
                    contentDescription = "Add subscription button",
                    tint = RassvetTheme.colors.logoColor
                )
            }
        }
    }
}