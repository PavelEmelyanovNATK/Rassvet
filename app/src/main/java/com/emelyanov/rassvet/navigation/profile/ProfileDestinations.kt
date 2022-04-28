package com.emelyanov.rassvet.navigation.profile

import com.emelyanov.rassvet.navigation.main.MainDestinations

sealed class ProfileDestinations(val route: String) {
    object Menu : ProfileDestinations("${MainDestinations.Profile.route}/menu")
    object Subscriptions : ProfileDestinations("${MainDestinations.Profile.route}/subscriptions")
    data class SubscriptionDetails(val id: Int) : ProfileDestinations("${MainDestinations.Profile.route}/subscription-details/$id")
    object SubscriptionDetailsCompRoute : ProfileDestinations("${MainDestinations.Profile.route}/subscription-details/{id}")
    object PopBack : ProfileDestinations("")
}