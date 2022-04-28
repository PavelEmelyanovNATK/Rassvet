package com.emelyanov.rassvet.navigation.authorization

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class AuthNavProvider : IAuthNavProvider {
    private val _currentDestination: MutableSharedFlow<AuthDestinations> = MutableSharedFlow(extraBufferCapacity = 1)
    override fun getDestinationFlow(): SharedFlow<AuthDestinations>
        = _currentDestination

    override fun navigateLogin() {
        _currentDestination.tryEmit(AuthDestinations.Login)
    }

    override fun navigateRegistration() {
        _currentDestination.tryEmit(AuthDestinations.Registration)
    }

    override fun popBack() {
        _currentDestination.tryEmit(AuthDestinations.PopBack)
    }
}