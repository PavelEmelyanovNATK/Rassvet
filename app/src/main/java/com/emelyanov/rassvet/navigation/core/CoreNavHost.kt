package com.emelyanov.rassvet.navigation.core

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.emelyanov.rassvet.modules.authorization.presentation.components.AuthorizationScreen
import com.emelyanov.rassvet.modules.core.domain.CoreViewModel
import com.emelyanov.rassvet.modules.core.presentation.LaunchingScreen
import com.emelyanov.rassvet.modules.firstboot.presentation.components.FirstBootScreen
import com.emelyanov.rassvet.modules.main.domain.MainViewModel
import com.emelyanov.rassvet.modules.main.presentation.components.MainScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun CoreNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    coreViewModel: CoreViewModel
) {

    LaunchedEffect(true) {
        coreViewModel.coreNavProvider.destinationFlow.onEach { destination ->
            destination?.let {
                if(destination is CoreDestinations.PopBack)
                    navHostController.popBackStack()
                else
                    navHostController.navigate(destination.route) {
                        launchSingleTop = true
                        navHostController.backQueue.clear()
                    }

                coreViewModel.coreNavProvider.navigated()
            }
        }.launchIn(this)
    }

    AnimatedNavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = CoreDestinations.Launching.route
    ) {
        composable(
            route = CoreDestinations.Launching.route,
            enterTransition = { null },
            exitTransition = { null },
            popEnterTransition = { null },
            popExitTransition = { null }
        ) {
            LaunchingScreen()
        }

        composable(
            route = CoreDestinations.FirstBoot.route,
            enterTransition = { null },
            exitTransition = { null },
            popEnterTransition = { null },
            popExitTransition = { null }
        ) {
            FirstBootScreen()
        }

        composable(
            route = CoreDestinations.Authorization.route,
            enterTransition = { null },
            exitTransition = { null },
            popEnterTransition = { null },
            popExitTransition = { null }
        ) {
            AuthorizationScreen(
                onLogInClick = {
                    coreViewModel.coreNavProvider.navigateTo(CoreDestinations.Main)
                }
            )
        }

        composable(
            route = CoreDestinations.Main.route,
            enterTransition = { null },
            exitTransition = { null },
            popEnterTransition = { null },
            popExitTransition = { null }
        ) {
            val mainViewModel = hiltViewModel<MainViewModel>()

            MainScreen(
                mainViewModel = mainViewModel
            )
        }
    }
}