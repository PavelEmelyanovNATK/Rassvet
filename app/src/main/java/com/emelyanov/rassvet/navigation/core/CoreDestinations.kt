package com.emelyanov.rassvet.navigation.core

import com.emelyanov.rassvet.navigation.authorization.AuthDestinations

sealed class CoreDestinations(val route: String) {
    object Launching : CoreDestinations("launching")
    object FirstBoot : CoreDestinations("first-boot")
    object Authorization : CoreDestinations("authorization")
    object Main : CoreDestinations("main")
    object PopBack : CoreDestinations("")
}
