package com.emelyanov.rassvet.navigation.firstboot

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.emelyanov.rassvet.modules.firstboot.domain.FirstBootViewModel
import com.emelyanov.rassvet.modules.firstboot.domain.FirstBootSectionDetailsViewModel
import com.emelyanov.rassvet.modules.firstboot.presentation.components.FirstBootContainer
import com.emelyanov.rassvet.shared.presentation.components.SectionDetailsScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun FirstBootNavHost(
    firstBootNavController: NavHostController
) {
    val firstBootViewModel = hiltViewModel<FirstBootViewModel>()

    val lifecycle = LocalLifecycleOwner.current
    LaunchedEffect(true) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            firstBootViewModel.firstBootNavProvider.destinationFlow.onEach { destination ->
                destination?.let {
                    if (destination is FirstBootDestinations.PopBack)
                        firstBootNavController.popBackStack()
                    else
                        firstBootNavController.navigate(destination.route) {
                            launchSingleTop = true
                        }

                    firstBootViewModel.firstBootNavProvider.navigated()
                }
            }.launchIn(this@repeatOnLifecycle)
        }
    }

    AnimatedNavHost(
        navController = firstBootNavController,
        startDestination = FirstBootDestinations.FirstBootScreen.route
    ) {
        composable(
            route =  FirstBootDestinations.FirstBootScreen.route
        ) {
            FirstBootContainer(
                sectionsListViewState = firstBootViewModel.sectionsListViewState.value,
                onAuthClick = firstBootViewModel::authClicked
            )
        }

        composable(
            route = FirstBootDestinations.SectionDetailsCompRoute.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType }),
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Up, animationSpec = spring())
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Down, animationSpec = spring())
            }
        ) { backStackEntry ->
            val sectionDetailsViewModel = hiltViewModel<FirstBootSectionDetailsViewModel>()
            val sectionId = backStackEntry.arguments?.getInt("id") ?: 0

            LaunchedEffect(true) {
                sectionDetailsViewModel.fetchSectionInfo(sectionId)
            }

            SectionDetailsScreen(
                viewState = sectionDetailsViewModel.sectionsListViewState.value,
                onBackClick = sectionDetailsViewModel::backClicked
            )
        }
    }
}