package com.emelyanov.rassvet.modules.main.presentation.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.emelyanov.rassvet.shared.presentation.components.SolidBackgroundBox
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.modules.main.domain.MainViewModel
import com.emelyanov.rassvet.modules.main.modules.profile.presentation.components.ProfileTab
import com.emelyanov.rassvet.modules.main.modules.subscriptions.presentation.SubscriptionsTab
import com.emelyanov.rassvet.modules.main.modules.trainings.presentation.components.TrainingDetailsScreen
import com.emelyanov.rassvet.modules.main.modules.trainings.presentation.components.TrainingsTab
import com.emelyanov.rassvet.navigation.main.MainNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi

const val NAV_BAR_PADDING = 10f

val LocalNavBarVisibilityState = compositionLocalOf { mutableStateOf(true) }

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun MainScreen(
    mainViewModel: MainViewModel
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val mainNavController = rememberAnimatedNavController()
        val navBarVisibilityState = remember { mutableStateOf(true) }

        CompositionLocalProvider(
            LocalNavBarVisibilityState provides navBarVisibilityState
        ) {
            MainNavHost(
                mainNavController = mainNavController,
                mainViewModel = mainViewModel
            )

            val navbarOffset = animateFloatAsState(
                targetValue = if(!LocalNavBarVisibilityState.current.value) NAV_BAR_HEIGHT + NAV_BAR_PADDING * 2 else 0f,
                animationSpec = spring(
                    dampingRatio = 0.6f,
                    stiffness = Spring.StiffnessMediumLow
                )
            )

            val curBackEntry = mainNavController.currentBackStackEntryAsState()

            RassvetNavBar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(NAV_BAR_PADDING.dp)
                    .offset(y = navbarOffset.value.dp),
                currentDestination = curBackEntry.value?.destination?.route ?: "",
                onTrainingsTabClick = mainViewModel::trainingsTabClick,
                onSubscriptionsTabClick = mainViewModel::subscriptionsTabClick,
                onProfileTabClick = mainViewModel::profileTabClick
            )
        }
    }
}