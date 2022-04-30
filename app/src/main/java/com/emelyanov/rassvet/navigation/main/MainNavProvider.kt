package com.emelyanov.rassvet.navigation.main

import com.emelyanov.rassvet.shared.domain.utils.BaseNavProvider
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class MainNavProvider() :
    BaseNavProvider<MainDestinations>()