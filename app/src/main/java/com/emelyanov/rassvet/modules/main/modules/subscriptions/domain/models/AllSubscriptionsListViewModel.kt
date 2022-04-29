package com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.modules.firstboot.domain.models.SectionsListViewState
import com.emelyanov.rassvet.navigation.firstboot.FirstBootNavProvider
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsDestinations
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsNavProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllSubscriptionsListViewModel
@Inject
constructor(
    private val _subscriptionsNavProvider: SubscriptionsNavProvider
) : ViewModel() {
    private val _sectionsListViewState: MutableState<SectionsListViewState> = mutableStateOf(
        SectionsListViewState.Loading)

    val sectionsListViewState: State<SectionsListViewState>
        get() = _sectionsListViewState

    init{
        fetchSections()
    }

    fun fetchSections() {
        viewModelScope.launch {
            _sectionsListViewState.value = SectionsListViewState.Loading

            delay(5000)

            _sectionsListViewState.value = SectionsListViewState.PresentSections(
                sections = (1..9).toList(),
                onSectionClick = { sectionId ->
                    _subscriptionsNavProvider.navigateTo(SubscriptionsDestinations.SubscriptionDetails(sectionId))
                }
            )
        }
    }
}