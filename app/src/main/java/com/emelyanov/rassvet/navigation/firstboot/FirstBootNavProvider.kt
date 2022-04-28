package com.emelyanov.rassvet.navigation.firstboot

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class FirstBootNavProvider : IFirstBootNavProvider {
    private val _currentDestination: MutableSharedFlow<FirstBootDestinations> = MutableSharedFlow(extraBufferCapacity = 1)
    override fun getDestinationFlow(): SharedFlow<FirstBootDestinations>
            = _currentDestination

    override fun navigateToFirstBoot() {
        _currentDestination.tryEmit(FirstBootDestinations.FirstBootScreen)
    }

    override fun navigateToSectionDetails(id: Int) {
        _currentDestination.tryEmit(FirstBootDestinations.SectionDetails(id))
    }

    override fun popBack() {
        _currentDestination.tryEmit(FirstBootDestinations.PopBack)
    }
}