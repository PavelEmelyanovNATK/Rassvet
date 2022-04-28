package com.emelyanov.rassvet.modules.core.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.navigation.core.CoreNavProvider
import com.emelyanov.rassvet.navigation.core.ICoreNavProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoreViewModel
@Inject
constructor(

) : ViewModel() {
    val coreNavProvider: ICoreNavProvider = CoreNavProvider()

    init {
        viewModelScope.launch {
            //delay(5000)
            coreNavProvider.navigateToFirstBoot()
        }
    }
}