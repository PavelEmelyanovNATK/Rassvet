package com.emelyanov.rassvet.navigation.main

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.emelyanov.rassvet.modules.main.domain.MainViewModel
import com.emelyanov.rassvet.modules.main.modules.profile.presentation.components.ProfileTab
import com.emelyanov.rassvet.modules.main.modules.subscriptions.presentation.SubscriptionsTab
import com.emelyanov.rassvet.modules.main.modules.trainings.presentation.components.TrainingsTab
import com.emelyanov.rassvet.modules.main.presentation.components.LocalNavBarVisibilityState
import com.emelyanov.rassvet.navigation.firstboot.FirstBootDestinations
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun MainNavHost(
    mainNavController: NavHostController,
    mainViewModel: MainViewModel
) {
    LaunchedEffect(key1 = true) {
        mainViewModel.mainNavProvider.destinationFlow.onEach { destination ->
            when (destination) {
                is MainDestinations.PopBack -> mainNavController.popBackStack()
                else -> mainNavController.navigate(destination.route) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(mainNavController.graph.startDestinationId) {
                        saveState = true
                    }
                }
            }
        }.launchIn(this)
    }

    AnimatedNavHost(
        modifier = Modifier.fillMaxSize(),
        navController = mainNavController,
        startDestination = MainDestinations.Trainings.route
    ) {
        composable(
            route = MainDestinations.Trainings.route,
            enterTransition = { null },
            exitTransition = { null },
            popEnterTransition = { null },
            popExitTransition = { null },
        ) {
            TrainingsTab()
        }

        composable(
            route = MainDestinations.Subscriptions.route,
            enterTransition = { null },
            exitTransition = { null },
            popEnterTransition = { null },
            popExitTransition = { null },

        ) {
            SubscriptionsTab()
        }

        composable(
            route = MainDestinations.Profile.route,
            enterTransition = { null },
            exitTransition = { null },
            popEnterTransition = { null },
            popExitTransition = { null },
        ) {
            ProfileTab()
        }
    }
}