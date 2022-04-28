package com.emelyanov.rassvet.modules.main.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.modules.main.domain.models.RassvetTabs
import com.emelyanov.rassvet.navigation.main.IMainNavProvider
import com.emelyanov.rassvet.navigation.main.MainDestinations
import com.emelyanov.rassvet.navigation.main.MainNavProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(

) : ViewModel() {
    val mainNavProvider: IMainNavProvider = MainNavProvider()

    init {
        mainNavProvider.getDestinationFlow().onEach { destination ->
            when(destination) {
                is MainDestinations.Trainings -> _selectedTab.value = RassvetTabs.Trainings
                is MainDestinations.Subscriptions -> _selectedTab.value = RassvetTabs.Subscriptions
                is MainDestinations.Profile -> _selectedTab.value = RassvetTabs.Profile
            }
        }.launchIn(viewModelScope)
    }

    private val _selectedTab: MutableState<RassvetTabs> = mutableStateOf(RassvetTabs.Trainings)
    val selectedTab: State<RassvetTabs>
        get() = _selectedTab

    fun navigateTo(destination: MainDestinations) {
        when(destination) {
            is MainDestinations.Trainings -> mainNavProvider.navigateToTrainings()
            is MainDestinations.Subscriptions -> mainNavProvider.navigateToSubscriptions()
            is MainDestinations.Profile -> mainNavProvider.navigateToProfile()
            is MainDestinations.PopBack -> mainNavProvider.popBack()
        }
    }
}