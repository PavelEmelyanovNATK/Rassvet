package com.emelyanov.rassvet.navigation.authorization

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.emelyanov.rassvet.modules.authorization.domain.AuthorizationViewModel
import com.emelyanov.rassvet.modules.authorization.presentation.components.LoginScreen
import com.emelyanov.rassvet.modules.authorization.presentation.components.RegistrationScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun AuthNavHost(
    modifier: Modifier = Modifier,
    authNavController: NavHostController,
    onLogInClick: () -> Unit
) {
    val authViewModel = hiltViewModel<AuthorizationViewModel>()

    LaunchedEffect(true) {
        authViewModel.authNavController.destinationFlow.onEach { destination ->
            destination?.let {
                if(destination is AuthDestinations.PopBack)
                    authNavController.popBackStack()
                else
                    authNavController.navigate(destination.route) { launchSingleTop = true }

                authViewModel.authNavController.navigated()
            }
        }.launchIn(this)
    }

    AnimatedNavHost(
        modifier = modifier,
        navController = authNavController,
        startDestination = AuthDestinations.Login.route
    ) {
        composable(AuthDestinations.Login.route) {
            LoginScreen(
                onCreateAccountClick = {
                    authViewModel.authNavController.navigateTo(AuthDestinations.Login)
                },
                onLogInClick = onLogInClick
            )
        }

        composable(AuthDestinations.Registration.route) {
            RegistrationScreen(
                onBackClick = {
                    authViewModel.authNavController.navigateTo(AuthDestinations.PopBack)
                }
            )
        }
    }
}