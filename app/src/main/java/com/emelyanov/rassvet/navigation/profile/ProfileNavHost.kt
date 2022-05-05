package com.emelyanov.rassvet.navigation.profile

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.emelyanov.rassvet.modules.main.modules.profile.domain.ProfileSectionsViewModel
import com.emelyanov.rassvet.modules.main.modules.profile.domain.ProfileSectionDetailsViewModel
import com.emelyanov.rassvet.modules.main.modules.profile.domain.ProfileViewModel
import com.emelyanov.rassvet.modules.main.modules.profile.presentation.components.ProfileMenuScreen
import com.emelyanov.rassvet.modules.main.modules.profile.presentation.components.ProfileSectionsScreen
import com.emelyanov.rassvet.shared.presentation.components.SectionDetailsScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun ProfileNavHost(
    profileNavController: NavHostController
) {
    val profileViewModel = hiltViewModel<ProfileViewModel>()

    LaunchedEffect(key1 = true) {
        profileViewModel.profileNavProvider.destinationFlow.onEach { destination ->
            destination?.let {
                if(destination is ProfileDestinations.PopBack)
                    profileNavController.popBackStack()
                else
                    profileNavController.navigate(destination.route) { launchSingleTop = true }

                profileViewModel.profileNavProvider.navigated()
            }
        }.launchIn(this)
    }

    AnimatedNavHost(
        modifier = Modifier.fillMaxSize(),
        navController = profileNavController,
        startDestination = ProfileDestinations.Menu.route
    ) {
        composable(
            route = ProfileDestinations.Menu.route
        ) {
            ProfileMenuScreen(
                viewState = profileViewModel.viewState.value,
                onAboutClick = {},
                onSectionsClick = {
                    profileViewModel.profileNavProvider.navigateTo(ProfileDestinations.Subscriptions)
                },
                onExitClick = profileViewModel::logoutClick
            )
        }

        composable(
            route = ProfileDestinations.Subscriptions.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Left)
            },
            exitTransition = {
                fadeOut(targetAlpha = 1f)
            },
            popEnterTransition = {
                fadeIn()
            },
            popExitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Right)
            }
        ) {
            val profileSectionsViewModel = hiltViewModel<ProfileSectionsViewModel>()

            ProfileSectionsScreen(
                onBackClick = {
                    profileViewModel.profileNavProvider.navigateTo(ProfileDestinations.PopBack)
                },
                profileSectionsViewState = profileSectionsViewModel.profileSectionsViewState.value
            )
        }

        composable(
            route = ProfileDestinations.SectionDetailsCompRoute.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType }),
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Left)
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Right)
            }
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            val subscriptionDetailsViewModel = hiltViewModel<ProfileSectionDetailsViewModel>()

            LaunchedEffect(key1 = true) {
                subscriptionDetailsViewModel.fetchInfo(id)
            }

            SectionDetailsScreen(
                viewState = subscriptionDetailsViewModel.viewState.value,
                onBackClick = subscriptionDetailsViewModel::backClick
            )
        }
    }
}