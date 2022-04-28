package com.emelyanov.rassvet.navigation.subscriptions

import com.emelyanov.rassvet.navigation.main.MainDestinations

sealed class SubscriptionsDestinations(val route: String) {
    object SubscriptionsList : SubscriptionsDestinations("${MainDestinations.Subscriptions.route}/list")
    data class SubscriptionDetails(val id: Int) : SubscriptionsDestinations("${MainDestinations.Subscriptions.route}/all-list/details/$id")
    object SubscriptionDetailsCompRoute : SubscriptionsDestinations("${MainDestinations.Subscriptions.route}/all-list/details/{id}")
    object PopBack : SubscriptionsDestinations("")
}