package com.emelyanov.rassvet.navigation.authorization

import com.emelyanov.rassvet.navigation.core.CoreDestinations

sealed class AuthDestinations(val route: String) {
    object Registration : AuthDestinations("${CoreDestinations.Authorization.route}/registration")
    object Login : AuthDestinations("${CoreDestinations.Authorization.route}/login")
    object PopBack : AuthDestinations("")
}