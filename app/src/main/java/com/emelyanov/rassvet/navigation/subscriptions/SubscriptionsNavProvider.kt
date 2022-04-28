package com.emelyanov.rassvet.navigation.subscriptions

import com.emelyanov.rassvet.navigation.trainings.TrainingsDestinations
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class SubscriptionsNavProvider : ISubscriptionsNavProvider {
    private val _currentDestination: MutableSharedFlow<SubscriptionsDestinations> = MutableSharedFlow(extraBufferCapacity = 1)
    override fun getDestinationFlow(): SharedFlow<SubscriptionsDestinations>
        = _currentDestination

    override fun navigateToSubscriptions() {
        _currentDestination.tryEmit(SubscriptionsDestinations.SubscriptionsList)
    }

    override fun navigateToDetails(id: Int) {
        _currentDestination.tryEmit(SubscriptionsDestinations.SubscriptionDetails(id))
    }

    override fun popBack() {
        _currentDestination.tryEmit(SubscriptionsDestinations.PopBack)
    }
}