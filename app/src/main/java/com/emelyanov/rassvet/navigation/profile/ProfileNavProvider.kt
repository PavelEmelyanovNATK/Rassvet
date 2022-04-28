package com.emelyanov.rassvet.navigation.profile

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileNavProvider : IProfileNavProvider {
    private val _currentDestination: MutableSharedFlow<ProfileDestinations> = MutableSharedFlow(extraBufferCapacity = 1)
    override fun getDestinationFlow(): SharedFlow<ProfileDestinations>
        = _currentDestination

    override fun navigateToMenu() {
        _currentDestination.tryEmit(ProfileDestinations.Menu)
    }

    override fun navigateToSubscriptions() {
        _currentDestination.tryEmit(ProfileDestinations.Subscriptions)
    }

    override fun navigateToSectionDetails(id: Int) {
        _currentDestination.tryEmit(ProfileDestinations.SubscriptionDetails(id))
    }

    override fun popBack() {
        _currentDestination.tryEmit(ProfileDestinations.PopBack)
    }
}