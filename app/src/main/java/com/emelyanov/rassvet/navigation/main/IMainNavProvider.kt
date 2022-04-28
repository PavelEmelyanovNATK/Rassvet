package com.emelyanov.rassvet.navigation.main

import com.emelyanov.rassvet.navigation.firstboot.FirstBootDestinations
import kotlinx.coroutines.flow.SharedFlow

interface IMainNavProvider {
    fun getDestinationFlow(): SharedFlow<MainDestinations>
    fun navigateToTrainings()
    fun navigateToSubscriptions()
    fun navigateToProfile()
    fun popBack()
}