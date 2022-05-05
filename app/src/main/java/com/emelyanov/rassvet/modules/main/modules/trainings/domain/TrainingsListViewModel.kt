package com.emelyanov.rassvet.modules.main.modules.trainings.domain

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.shared.domain.models.Section
import com.emelyanov.rassvet.modules.main.modules.trainings.domain.models.TrainingsListViewState
import com.emelyanov.rassvet.modules.main.modules.trainings.domain.usecases.*
import com.emelyanov.rassvet.shared.domain.usecases.NavigateToAllSubscriptionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrainingsListViewModel
@Inject
constructor(
    private val getActiveTrainings: GetActiveTrainingsUseCase,
    private val getActiveTrainingsBySection: GetActiveTrainingsBySectionUseCase,
    private val getPastTrainings: GetPastTrainingsUseCase,
    private val getPastTrainingsBySection: GetPastTrainingsBySectionUseCase,
    private val getClientSections: GetClientSectionsUseCase,
    private val sortTrainingsBySection: SortTrainingsBySectionUseCase,
    private val navigateToTrainingDetails: NavigateToTrainingDetailsUseCase,
    private val navigateToAllSubscriptions: NavigateToAllSubscriptionsUseCase
) : ViewModel() {
    private val _trainingsListViewState: MutableState<TrainingsListViewState> = mutableStateOf(TrainingsListViewState.Loading)
    val trainingsListViewState: State<TrainingsListViewState>
        get() = _trainingsListViewState

    init {
        refresh()
    }

    private fun sectionChanged(section: Section) {
        val state = _trainingsListViewState.value

        if(state is TrainingsListViewState.PresentInfo){
            state.selectedSection.value = section
            refresh()
        }

    }

    fun refresh() {
        viewModelScope.launch {
            val state = _trainingsListViewState.value

            try{
                when(state) {
                    is TrainingsListViewState.PresentInfo -> regularLoad(state)
                    else -> firstLoad()
                }
            } catch (ex: Exception) {
                _trainingsListViewState.value = TrainingsListViewState.Error(ex.message ?: "Неописанная ошибка: ${ex::class.simpleName}")
            }
        }
    }

    private suspend fun firstLoad() {
        val sections = getClientSections()
        Log.d("SectionsLoaded", "True")

        if(!sections.any { it.id > 0}) {
            _trainingsListViewState.value = TrainingsListViewState.NoSubscriptions(
                onSubscriptionsClick = ::goToSubscriptions
            )
            return
        }

        val selectedSection = sections.first { s -> s.id == -1 }

        val activeTrainings = when(selectedSection.id) {
            -1 -> sortTrainingsBySection(getActiveTrainings(), sections)
            else -> sortTrainingsBySection(
                getActiveTrainingsBySection(selectedSection.id),
                sections
            )
        }
        Log.d("ActiveTrainingsLoaded", "True")

        val pastTrainings = when(selectedSection.id) {
            -1 -> getPastTrainings(1)
            else -> getPastTrainingsBySection(selectedSection.id, 1)
        }
        Log.d("PastTrainingsLoaded", "True")

        _trainingsListViewState.value = TrainingsListViewState.PresentInfo(
            activeTrainings = activeTrainings,
            pastTrainings = pastTrainings,
            sections = sections,
            selectedSection = mutableStateOf(selectedSection),
            onTrainingClick = navigateToTrainingDetails::invoke,
            onSectionChange = ::sectionChanged
        )
    }

    private suspend fun regularLoad(prevState: TrainingsListViewState.PresentInfo) {
        _trainingsListViewState.value = TrainingsListViewState.Loading

        delay(200)
        val sections = getClientSections()

        if(!sections.any { it.id > 0}) {
            _trainingsListViewState.value = TrainingsListViewState.NoSubscriptions(
                onSubscriptionsClick = ::goToSubscriptions
            )
            return
        }

        val selectedSection = if(sections.any { it.id == prevState.selectedSection.value.id })
            prevState.selectedSection.value
        else
            sections.first { s -> s.id == -1 }

        val activeTrainings = when(selectedSection.id) {
            -1 -> sortTrainingsBySection(getActiveTrainings(), sections)
            else -> sortTrainingsBySection(
                getActiveTrainingsBySection(selectedSection.id),
                sections
            )
        }

        val pastTrainings = when(selectedSection.id) {
            -1 -> getPastTrainings(1)
            else -> getPastTrainingsBySection(selectedSection.id, 1)
        }

        _trainingsListViewState.value = TrainingsListViewState.PresentInfo(
            pagesCount = prevState.pagesCount,
            activeTrainings = activeTrainings,
            pastTrainings = pastTrainings,
            sections = sections,
            selectedSection = mutableStateOf(selectedSection),
            onTrainingClick = navigateToTrainingDetails::invoke,
            onSectionChange = ::sectionChanged
        )
    }

    private fun goToSubscriptions() = navigateToAllSubscriptions()
}