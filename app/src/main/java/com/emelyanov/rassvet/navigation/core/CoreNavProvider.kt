package com.emelyanov.rassvet.navigation.core

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class CoreNavProvider : ICoreNavProvider {
    private val _currentDestination: MutableSharedFlow<CoreDestinations> = MutableSharedFlow(
        replay = 1,
        extraBufferCapacity = 1
    )
    override fun getDestinationFlow(): SharedFlow<CoreDestinations>
        = _currentDestination

    override fun navigateToLaunching() {
        _currentDestination.tryEmit(CoreDestinations.Launching)
    }

    override fun navigateToFirstBoot() {
        _currentDestination.tryEmit(CoreDestinations.FirstBoot)
    }

    override fun navigateToAuth() {
        _currentDestination.tryEmit(CoreDestinations.Authorization)
    }

    override fun navigateToMain() {
        _currentDestination.tryEmit(CoreDestinations.Main)
    }

    override fun popBack() {
        _currentDestination.tryEmit(CoreDestinations.PopBack)
    }


}