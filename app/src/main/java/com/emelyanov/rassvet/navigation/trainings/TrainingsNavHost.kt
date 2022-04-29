package com.emelyanov.rassvet.navigation.trainings

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.emelyanov.rassvet.modules.main.modules.trainings.domain.TrainingsListViewModel
import com.emelyanov.rassvet.modules.main.modules.trainings.domain.TrainingsViewModel
import com.emelyanov.rassvet.modules.main.modules.trainings.presentation.components.TrainingDetailsScreen
import com.emelyanov.rassvet.modules.main.modules.trainings.presentation.components.TrainingsListScreen
import com.emelyanov.rassvet.modules.main.presentation.components.LocalNavBarVisibilityState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun TrainingsNavHost(
    trainingsNavController: NavHostController
) {
    val trainingsViewModel = hiltViewModel<TrainingsViewModel>()

    LaunchedEffect(true) {
        trainingsViewModel.trainingsNavProvider.destinationFlow.onEach { destination ->
            if(destination is TrainingsDestinations.PopBack)
                trainingsNavController.popBackStack()
            else
                trainingsNavController.navigate(destination.route) { launchSingleTop = true }
        }.launchIn(this)
    }

    AnimatedNavHost(
        modifier = Modifier.fillMaxSize(),
        navController = trainingsNavController,
        startDestination = TrainingsDestinations.TrainingsList.route
    ) {
        composable(
            route = TrainingsDestinations.TrainingsList.route,
            enterTransition = { null },
            exitTransition = { null },
            popEnterTransition = { null },
            popExitTransition = { null }
        ) {
            LocalNavBarVisibilityState.current.value = true
            val trainingsListViewModel = hiltViewModel<TrainingsListViewModel>()

            TrainingsListScreen(
                trainingsListViewState = trainingsListViewModel.trainingsListViewState.value,
                onRefresh = trainingsListViewModel::refresh
            )
        }

        composable(
            route = TrainingsDestinations.TrainingDetailsCompRoute.route,
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
            LocalNavBarVisibilityState.current.value = false
            TrainingDetailsScreen(
                onBackClick = {
                    trainingsViewModel.trainingsNavProvider.navigateTo(TrainingsDestinations.PopBack)
                }
            )
        }
    }
}