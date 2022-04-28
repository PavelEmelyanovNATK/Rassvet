package com.emelyanov.rassvet.navigation.firstboot

import kotlinx.coroutines.flow.SharedFlow

interface IFirstBootNavProvider {
    fun getDestinationFlow(): SharedFlow<FirstBootDestinations>
    fun navigateToFirstBoot()
    fun navigateToSectionDetails(id: Int)
    fun popBack()
}