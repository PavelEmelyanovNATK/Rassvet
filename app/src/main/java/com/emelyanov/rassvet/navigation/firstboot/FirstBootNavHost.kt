package com.emelyanov.rassvet.navigation.firstboot

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.navigation
import com.emelyanov.rassvet.modules.firstboot.domain.FirstBootViewModel
import com.emelyanov.rassvet.modules.firstboot.domain.SectionDetailsViewModel
import com.emelyanov.rassvet.modules.firstboot.presentation.components.FirstBootContainer
import com.emelyanov.rassvet.modules.firstboot.presentation.components.SectionDetailsScreen
import com.emelyanov.rassvet.navigation.core.CoreDestinations
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
    firstBootNavController: NavHostController,
    onAuthClick: () -> Unit,
) {
    val firstBootViewModel = hiltViewModel<FirstBootViewModel>()

    LaunchedEffect(true) {
        firstBootViewModel.fetchSections()

        firstBootViewModel.firstBootNavProvider.destinationFlow.onEach { destination ->
            if(destination is FirstBootDestinations.PopBack)
                firstBootNavController.popBackStack()
            else
                firstBootNavController.navigate(destination.route) { launchSingleTop = true }
        }.launchIn(this)
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
                onAuthClick = onAuthClick
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
            val sectionDetailsViewModel = hiltViewModel<SectionDetailsViewModel>()
            val sectionId = backStackEntry.arguments?.getInt("id") ?: 0

            LaunchedEffect(true) {
                sectionDetailsViewModel.fetchSectionInfo(sectionId)
            }

            SectionDetailsScreen(
                sectionDetailsViewState = sectionDetailsViewModel.sectionsListViewState.value,
                onAuthClick = onAuthClick,
                onBackClick = {
                    firstBootViewModel.firstBootNavProvider.navigateTo(FirstBootDestinations.PopBack)
                }
            )
        }
    }
}