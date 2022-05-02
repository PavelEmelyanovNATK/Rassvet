package com.emelyanov.rassvet.modules.authorization.presentation.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.emelyanov.rassvet.navigation.authorization.AuthNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun AuthorizationScreen(
    onLogInClick: () -> Unit
) {
    val authNavControlelr = rememberAnimatedNavController()

    AuthNavHost(
        authNavController = authNavControlelr,
        onLogInClick = onLogInClick
    )
}