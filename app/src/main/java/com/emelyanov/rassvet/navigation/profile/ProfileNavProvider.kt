package com.emelyanov.rassvet.navigation.profile

import com.emelyanov.rassvet.shared.domain.utils.BaseNavProvider
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileNavProvider(startDestination: ProfileDestinations) :
    BaseNavProvider<ProfileDestinations>(startDestination)