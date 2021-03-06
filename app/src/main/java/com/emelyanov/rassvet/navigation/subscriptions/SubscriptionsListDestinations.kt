package com.emelyanov.rassvet.navigation.subscriptions

sealed class SubscriptionsListDestinations(val route: String) {
    object ClientSubscriptions : SubscriptionsListDestinations("${SubscriptionsDestinations.SubscriptionsList.route}/client")
    object Sections : SubscriptionsListDestinations("${SubscriptionsDestinations.SubscriptionsList.route}/all")
    object PopBack : SubscriptionsListDestinations("")
}