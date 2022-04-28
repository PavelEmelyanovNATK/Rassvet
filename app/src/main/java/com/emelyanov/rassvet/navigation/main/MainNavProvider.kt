package com.emelyanov.rassvet.navigation.main

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class MainNavProvider : IMainNavProvider {
    private val _currentDestination: MutableSharedFlow<MainDestinations> = MutableSharedFlow(extraBufferCapacity = 1)
    override fun getDestinationFlow(): SharedFlow<MainDestinations>
        = _currentDestination

    override fun navigateToTrainings() {
        _currentDestination.tryEmit(MainDestinations.Trainings)
    }

    override fun navigateToSubscriptions() {
        _currentDestination.tryEmit(MainDestinations.Subscriptions)
    }

    override fun navigateToProfile() {
        _currentDestination.tryEmit(MainDestinations.Profile)
    }

    override fun popBack() {
        _currentDestination.tryEmit(MainDestinations.PopBack)
    }
}