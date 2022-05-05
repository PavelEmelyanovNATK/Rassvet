package com.emelyanov.rassvet.navigation.main

import com.emelyanov.rassvet.navigation.core.CoreDestinations

sealed class MainDestinations(val route: String) {
    object Trainings : MainDestinations("${CoreDestinations.Main.route}/trainings")
    object Subscriptions : MainDestinations("${CoreDestinations.Main.route}/subscriptions")
    object Profile : MainDestinations("${CoreDestinations.Main.route}/profile")
    object PopBack : MainDestinations("")
}