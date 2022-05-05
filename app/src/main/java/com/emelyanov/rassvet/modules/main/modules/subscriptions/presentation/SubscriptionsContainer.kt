package com.emelyanov.rassvet.modules.main.modules.subscriptions.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.modules.main.presentation.components.LocalNavBarVisibilityState
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsListNavHost
import com.emelyanov.rassvet.shared.presentation.components.SolidBackgroundBox
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

const val TOOL_BAR_HEIGHT = 50f

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun SubscriptionsContainer(
    onAddButtonClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val subscriptionsListNavController = rememberAnimatedNavController()

    LocalNavBarVisibilityState.current.value = true

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = RassvetTheme.colors.toolbarBackground,
                    start = Offset(0f, Float.POSITIVE_INFINITY),
                    end = Offset(Float.POSITIVE_INFINITY, 0f)
                )
            )
    ) {
        SolidBackgroundBox(
            modifier = Modifier
                .padding(top = TOOL_BAR_HEIGHT.dp)
                .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
        ) {
            SubscriptionsListNavHost(
                subscriptionsListNavController = subscriptionsListNavController
            )
        }

        SubscriptionsTopBar(
            subscriptionsListNavController = subscriptionsListNavController,
            onAddSubClick = onAddButtonClick,
            onBackClick = onBackClick
        )
    }
}