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
import com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.ClientSubscriptionsListViewModel
import com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.SubscriptionsContainerViewModel
import com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.AllSubscriptionsListViewModel
import com.emelyanov.rassvet.modules.main.modules.subscriptions.presentation.AllSubscriptionsPage
import com.emelyanov.rassvet.modules.main.modules.subscriptions.presentation.ClientSubscriptionsPage
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun SubscriptionsListNavHost(
    subscriptionsListNavController: NavHostController
) {
    val subscriptionsContainerViewModel = hiltViewModel<SubscriptionsContainerViewModel>()

    LaunchedEffect(key1 = true) {
        subscriptionsContainerViewModel.subscriptionsListNavProvider.destinationFlow.onEach { destination ->
            destination?.let {
                if(destination is SubscriptionsListDestinations.PopBack)
                    subscriptionsListNavController.popBackStack()
                else
                    subscriptionsListNavController.navigate(destination.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(subscriptionsListNavController.graph.startDestinationId) {
                            saveState = true
                        }
                    }

                subscriptionsContainerViewModel.subscriptionsListNavProvider.navigated()
            }
        }.launchIn(this)
    }

    AnimatedNavHost(
        modifier = Modifier.fillMaxSize(),
        navController =  subscriptionsListNavController,
        startDestination = SubscriptionsListDestinations.ClientSubscriptions.route
    ) {
        composable(
            route = SubscriptionsListDestinations.ClientSubscriptions.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Up, animationSpec = spring())
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Down, animationSpec = spring())
            }
        ) {
            val clientSubsViewModel = hiltViewModel<ClientSubscriptionsListViewModel>()

            ClientSubscriptionsPage(
                clientSubscriptionsListViewState = clientSubsViewModel.clientSubscriptionsListViewState.value,
                onRefresh = clientSubsViewModel::refresh
            )
        }

        composable(
            route = SubscriptionsListDestinations.AllSubscriptions.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Up, animationSpec = spring())
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Down, animationSpec = spring())
            }
        ) {
            val allSubscriptionsListViewModel = hiltViewModel<AllSubscriptionsListViewModel>()

            AllSubscriptionsPage(sectionsListViewState = allSubscriptionsListViewModel.sectionsListViewState.value)
        }
    }
}