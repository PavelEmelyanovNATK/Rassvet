package com.emelyanov.rassvet.navigation.core

import androidx.compose.runtime.State
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.SharedFlow

interface ICoreNavProvider {
    fun getDestinationFlow(): SharedFlow<CoreDestinations>
    fun navigateToLaunching()
    fun navigateToFirstBoot()
    fun navigateToAuth()
    fun navigateToMain()
    fun popBack()
}