package com.emelyanov.rassvet.modules.main.modules.subscriptions.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.usecases.NavigateToSectionDetailsUseCase
import com.emelyanov.rassvet.shared.domain.models.SectionsListViewState
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsDestinations
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsNavProvider
import com.emelyanov.rassvet.shared.domain.usecases.GetAllSectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SectionsListViewModel
@Inject
constructor(
    private val navigateToSectionDetails: NavigateToSectionDetailsUseCase,
    private val getAllSections: GetAllSectionsUseCase
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

            try {
                getAllSections().let {
                    _sectionsListViewState.value = SectionsListViewState.PresentInfo(
                        sections = it,
                        onSectionClick = navigateToSectionDetails::invoke
                    )
                }
            } catch (ex: Exception) {
                _sectionsListViewState.value = SectionsListViewState.Error(
                    message = ex.message ?: "Неописанная ошибка: ${ex::class.java.simpleName}"
                )
            }
        }
    }
}