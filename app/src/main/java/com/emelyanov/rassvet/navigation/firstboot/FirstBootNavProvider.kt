package com.emelyanov.rassvet.navigation.firstboot

import com.emelyanov.rassvet.shared.domain.utils.BaseNavProvider
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class FirstBootNavProvider :
    BaseNavProvider<FirstBootDestinations>()