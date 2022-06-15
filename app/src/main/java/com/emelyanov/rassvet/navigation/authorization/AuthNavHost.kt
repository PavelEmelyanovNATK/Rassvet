package com.emelyanov.rassvet.navigation.authorization

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import com.emelyanov.rassvet.modules.authorization.domain.AuthorizationViewModel
import com.emelyanov.rassvet.modules.authorization.domain.LoginViewModel
import com.emelyanov.rassvet.modules.authorization.domain.RegistrationViewModel
import com.emelyanov.rassvet.modules.authorization.presentation.components.LoginScreen
import com.emelyanov.rassvet.modules.authorization.presentation.components.RegistrationScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.math.log

@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun AuthNavHost(
    modifier: Modifier = Modifier,
    authNavController: NavHostController
) {
    val authViewModel = hiltViewModel<AuthorizationViewModel>()

    val lifecycle = LocalLifecycleOwner.current
    LaunchedEffect(true) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            authViewModel.authNavController.destinationFlow.onEach { destination ->
                destination?.let {
                    if (destination is AuthDestinations.PopBack)
                        authNavController.popBackStack()
                    else
                        authNavController.navigate(destination.route) { launchSingleTop = true }

                    authViewModel.authNavController.navigated()
                }
            }.launchIn(this@repeatOnLifecycle)
        }
    }

    AnimatedNavHost(
        modifier = modifier,
        navController = authNavController,
        startDestination = AuthDestinations.Login.route
    ) {
        composable(AuthDestinations.Login.route) {
            val loginViewModel = hiltViewModel<LoginViewModel>()

            LoginScreen(
                loginViewState = loginViewModel.viewState.value,
                loginViewModel.notificationsFlow,
                onNotificationProcessed = loginViewModel::onNotificationProcessed
            )
        }

        composable(AuthDestinations.Registration.route) {
            val registrationViewModel = hiltViewModel<RegistrationViewModel>()

            RegistrationScreen(
                registrationViewModel.viewState.value,
                registrationNotificationFlow = registrationViewModel.notificationsFlow,
                onNotificationProcessed = registrationViewModel::onNotificationProcessed
            )
        }
    }
}