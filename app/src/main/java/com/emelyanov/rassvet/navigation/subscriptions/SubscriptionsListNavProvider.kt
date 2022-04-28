package com.emelyanov.rassvet.navigation.subscriptions

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class SubscriptionsListNavProvider : ISubscriptionsListNavProvider {
    private val _currentDestination: MutableSharedFlow<SubscriptionsListDestinations> = MutableSharedFlow(extraBufferCapacity = 1)
    override fun getDestinationFlow(): SharedFlow<SubscriptionsListDestinations>
        = _currentDestination

    override fun navigateToClientSubscriptions() {
        _currentDestination.tryEmit(SubscriptionsListDestinations.ClientSubscriptions)
    }

    override fun navigateToAllSubscriptions() {
        _currentDestination.tryEmit(SubscriptionsListDestinations.AllSubscriptions)
    }

    override fun popBack() {
        _currentDestination.tryEmit(SubscriptionsListDestinations.PopBack)
    }
}