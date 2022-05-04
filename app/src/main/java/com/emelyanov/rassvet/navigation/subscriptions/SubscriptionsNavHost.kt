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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.SubscriptionsViewModel
import com.emelyanov.rassvet.modules.main.modules.subscriptions.presentation.SubscriptionsContainer
import com.emelyanov.rassvet.modules.main.modules.subscriptions.presentation.SubscriptionsTab
import com.emelyanov.rassvet.navigation.firstboot.FirstBootDestinations
import com.emelyanov.rassvet.navigation.main.MainDestinations
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun SubscriptionsNavHost(
    subscriptionsNavController: NavHostController
) {
    val subscriptionsViewModel = hiltViewModel<SubscriptionsViewModel>()

    LaunchedEffect(key1 = true) {
        subscriptionsViewModel.subscriptionsNavProvider.destinationFlow.onEach { destination ->
            destination?.let {
                if(destination is SubscriptionsDestinations.PopBack)
                    subscriptionsNavController.popBackStack()
                else
                    subscriptionsNavController.navigate(destination.route) {
                        launchSingleTop = true
                    }

                subscriptionsViewModel.subscriptionsNavProvider.navigated()
            }
        }.launchIn(this)
    }

    AnimatedNavHost(
        modifier = Modifier.fillMaxSize(),
        navController =  subscriptionsNavController,
        startDestination = SubscriptionsDestinations.SubscriptionsList.route
    ) {
        composable(
            route = SubscriptionsDestinations.SubscriptionsList.route
        ) {
            SubscriptionsContainer(
                onAddButtonClick ={
                    subscriptionsViewModel.subscriptionsListNavProvider.navigateTo(SubscriptionsListDestinations.AllSubscriptions)
                },
                onBackClick = {
                    subscriptionsViewModel.subscriptionsListNavProvider.navigateTo(SubscriptionsListDestinations.PopBack)
                }
            )
        }

        composable(
            route = SubscriptionsDestinations.SubscriptionDetailsCompRoute.route,
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
        ) {

        }
    }
}