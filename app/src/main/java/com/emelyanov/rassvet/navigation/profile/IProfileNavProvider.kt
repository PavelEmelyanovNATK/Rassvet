package com.emelyanov.rassvet.navigation.profile

import com.emelyanov.rassvet.navigation.main.MainDestinations
import kotlinx.coroutines.flow.SharedFlow

interface IProfileNavProvider {
    fun getDestinationFlow(): SharedFlow<ProfileDestinations>
    fun navigateToMenu()
    fun navigateToSubscriptions()
    fun navigateToSectionDetails(id: Int)
    fun popBack()
}