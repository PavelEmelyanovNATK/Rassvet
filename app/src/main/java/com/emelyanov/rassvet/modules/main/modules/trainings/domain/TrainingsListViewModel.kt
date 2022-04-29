package com.emelyanov.rassvet.modules.main.modules.trainings.domain

import androidx.compose.animation.defaultDecayAnimationSpec
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.modules.main.modules.trainings.domain.models.TrainingsListViewState
import com.emelyanov.rassvet.navigation.trainings.TrainingsDestinations
import com.emelyanov.rassvet.navigation.trainings.TrainingsNavProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrainingsListViewModel
@Inject
constructor(
    private val _trainingsNavProvider: TrainingsNavProvider
) : ViewModel() {
    private val _trainingsListViewState: MutableState<TrainingsListViewState> = mutableStateOf(TrainingsListViewState.Loading)
    val trainingsListViewState: State<TrainingsListViewState>
        get() = _trainingsListViewState

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            val state = _trainingsListViewState.value

            if(state is TrainingsListViewState.PresentInfo) {
                _trainingsListViewState.value = TrainingsListViewState.Loading
                delay(2000)
                var tempSelectedSectionId = state.selectedSection

                val sections = listOf(-1) + (1..5).toList()
                if(!sections.contains(tempSelectedSectionId))
                    tempSelectedSectionId = -1

                val activeTrainings = (1..10).toList()
                val groupedActiveTrainings = mutableMapOf<Int, MutableList<Int>>()
                var curGroup = 1

                activeTrainings.forEachIndexed { i, t ->
                    if(i != 0 && i % 4 == 0)
                        curGroup++

                    if(groupedActiveTrainings.keys.contains(curGroup)) {
                        groupedActiveTrainings[curGroup]?.add(t)
                    }
                    else {
                        groupedActiveTrainings[curGroup] = mutableListOf(t)
                    }
                }

                val pastTrainings = (1..12).toList()

                //_trainingsListViewState.value = TrainingsListViewState.PresentInfo(
                //    activeTrainings = groupedActiveTrainings,
                //    pastTrainings = pastTrainings,
                //    sections = sections,
                //    selectedSection = tempSelectedSectionId,
                //    onTrainingClick = _trainingsNavProvider::navigateToDetails
                //)

                _trainingsListViewState.value = TrainingsListViewState.NoSubscriptions
            }
            else {
                _trainingsListViewState.value = TrainingsListViewState.Loading
                delay(2000)
                val sections = listOf(-1) + (1..5).toList()

                val activeTrainings = (1..10).toList()
                val groupedActiveTrainings = mutableMapOf<Int, MutableList<Int>>()
                var curGroup = 1

                activeTrainings.forEachIndexed { i, t ->
                    if(i != 0 && i % 4 == 0)
                        curGroup++

                    if(groupedActiveTrainings.keys.contains(curGroup)) {
                        groupedActiveTrainings[curGroup]?.add(t)
                    }
                    else {
                        groupedActiveTrainings[curGroup] = mutableListOf(t)
                    }
                }

                val pastTrainings = (1..12).toList()

                _trainingsListViewState.value = TrainingsListViewState.PresentInfo(
                    activeTrainings = groupedActiveTrainings,
                    pastTrainings = pastTrainings,
                    sections = sections,
                    selectedSection = -1,
                    onTrainingClick = {
                        _trainingsNavProvider.navigateTo(TrainingsDestinations.TrainingDetails(it))
                    }
                )
            }
        }
    }
}