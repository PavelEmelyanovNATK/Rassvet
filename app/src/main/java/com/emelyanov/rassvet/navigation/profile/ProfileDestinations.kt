package com.emelyanov.rassvet.navigation.profile

import com.emelyanov.rassvet.navigation.main.MainDestinations

sealed class ProfileDestinations(val route: String) {
    object Menu : ProfileDestinations("${MainDestinations.Profile.route}/menu")
    object Subscriptions : ProfileDestinations("${MainDestinations.Profile.route}/subscriptions")
    data class SectionDetails(val id: Int) : ProfileDestinations("${MainDestinations.Profile.route}/subscription-details/$id")
    object SectionDetailsCompRoute : ProfileDestinations("${MainDestinations.Profile.route}/subscription-details/{id}")
    object PopBack : ProfileDestinations("")
}