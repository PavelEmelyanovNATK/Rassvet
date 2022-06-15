package com.emelyanov.rassvet.navigation.subscriptions

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.SectionDetailsViewModel
import com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.SubscriptionsViewModel
import com.emelyanov.rassvet.modules.main.modules.subscriptions.presentation.SubscriptionsContainer
import com.emelyanov.rassvet.modules.main.presentation.components.LocalNavBarVisibilityState
import com.emelyanov.rassvet.shared.presentation.components.SectionDetailsScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun SubscriptionsNavHost(
    subscriptionsNavController: NavHostController
) {
    val subscriptionsViewModel = hiltViewModel<SubscriptionsViewModel>()

    val lifecycle = LocalLifecycleOwner.current
    LaunchedEffect(true) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            subscriptionsViewModel.subscriptionsNavProvider.destinationFlow.onEach { destination ->
                destination?.let {
                    if (destination is SubscriptionsDestinations.PopBack)
                        subscriptionsNavController.popBackStack()
                    else
                        subscriptionsNavController.navigate(destination.route) {
                            launchSingleTop = true
                        }

                    subscriptionsViewModel.subscriptionsNavProvider.navigated()
                }
            }.launchIn(this@repeatOnLifecycle)
        }
    }

    AnimatedNavHost(
        modifier = Modifier.fillMaxSize(),
        navController =  subscriptionsNavController,
        startDestination = SubscriptionsDestinations.SubscriptionsList.route
    ) {
        composable(
            route = SubscriptionsDestinations.SubscriptionsList.route
        ) {
            LocalNavBarVisibilityState.current.value = true

            SubscriptionsContainer(
                onAddButtonClick = subscriptionsViewModel::addSubscriptionCLick,
                onBackClick = subscriptionsViewModel::backFromSubscriptionsClick
            )
        }

        composable(
            route = SubscriptionsDestinations.SubscriptionDetailsCompRoute.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType }),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentScope.SlideDirection.Left,
                    animationSpec = spring()
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentScope.SlideDirection.Right,
                    animationSpec = spring()
                )
            },
            popEnterTransition = { null },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentScope.SlideDirection.Right,
                    animationSpec = spring()
                )
            }
        ) { backStackEntry ->
            LocalNavBarVisibilityState.current.value = false

            val sectionDetailsViewModel = hiltViewModel<SectionDetailsViewModel>()
            val id = backStackEntry.arguments?.getInt("id") ?: 0

            LaunchedEffect(key1 = true) {
                sectionDetailsViewModel.fetchInfo(id)
            }

            SectionDetailsScreen(
                viewState = sectionDetailsViewModel.viewState.value,
                onBackClick = sectionDetailsViewModel::backClick
            )
        }
    }
}