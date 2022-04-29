package com.emelyanov.rassvet.modules.core.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.navigation.core.CoreDestinations
import com.emelyanov.rassvet.navigation.core.CoreNavProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoreViewModel
@Inject
constructor(
    val coreNavProvider: CoreNavProvider
) : ViewModel() {

    init {
        viewModelScope.launch {
            //delay(5000)
            coreNavProvider.navigateTo(CoreDestinations.FirstBoot)
        }
    }
}