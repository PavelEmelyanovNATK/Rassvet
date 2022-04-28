package com.emelyanov.rassvet.navigation.authorization

import androidx.compose.runtime.State
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface IAuthNavProvider {
    fun getDestinationFlow(): SharedFlow<AuthDestinations>
    fun navigateLogin()
    fun navigateRegistration()
    fun popBack()
}