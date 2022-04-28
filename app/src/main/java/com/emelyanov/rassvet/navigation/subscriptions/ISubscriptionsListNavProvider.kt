package com.emelyanov.rassvet.navigation.subscriptions

import kotlinx.coroutines.flow.SharedFlow

interface ISubscriptionsListNavProvider {
    fun getDestinationFlow(): SharedFlow<SubscriptionsListDestinations>
    fun navigateToClientSubscriptions()
    fun navigateToAllSubscriptions()
    fun popBack()
}