package com.emelyanov.rassvet.navigation.trainings

import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.emelyanov.rassvet.modules.main.modules.trainings.domain.TrainingDetailsViewModel
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

    val lifecycle = LocalLifecycleOwner.current
    LaunchedEffect(true) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            trainingsViewModel.trainingsNavProvider.destinationFlow.onEach { destination ->
                destination?.let {
                    if (destination is TrainingsDestinations.PopBack)
                        trainingsNavController.popBackStack()
                    else
                        trainingsNavController.navigate(destination.route) {
                            launchSingleTop = true
                        }

                    trainingsViewModel.trainingsNavProvider.navigated()
                }
            }.launchIn(this@repeatOnLifecycle)
        }
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

            val trainingDetailsViewModel = hiltViewModel<TrainingDetailsViewModel>()
            val id = backStackEntry.arguments?.getInt("id") ?: 0

            LaunchedEffect(key1 = true) {
                trainingDetailsViewModel.fetchInfo(id)
            }

            TrainingDetailsScreen(
                trainingDetailsViewState = trainingDetailsViewModel.viewState.value,
                onBackClick = trainingDetailsViewModel::backClick
            )
        }
    }
}