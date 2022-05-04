package com.emelyanov.rassvet.modules.main.modules.trainings.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.modules.main.modules.trainings.domain.models.TrainingDetailsViewState
import com.emelyanov.rassvet.modules.main.modules.trainings.domain.usecases.GetSectionDetailsUseCase
import com.emelyanov.rassvet.modules.main.modules.trainings.domain.usecases.PopBackFromDetailsUseCase
import com.emelyanov.rassvet.navigation.trainings.TrainingsDestinations
import com.emelyanov.rassvet.navigation.trainings.TrainingsNavProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrainingDetailsViewModel
@Inject
constructor(
    private val getSectionDetails: GetSectionDetailsUseCase,
    private val popBack: PopBackFromDetailsUseCase
) : ViewModel() {
    private val _viewState: MutableState<TrainingDetailsViewState>
    = mutableStateOf(TrainingDetailsViewState.Loading)
    val viewState: State<TrainingDetailsViewState>
        get() = _viewState

    fun backClick() = popBack()

    fun fetchInfo(id: Int) {
        viewModelScope.launch {
            _viewState.value = TrainingDetailsViewState.Loading

            try{
                getSectionDetails(id).let {
                    _viewState.value = TrainingDetailsViewState.PresentInfo(
                        title = it.title,
                        room = it.room,
                        section = it.section,
                        startDate = it.startDate,
                        description = it.description,
                        durationInMinutes = it.durationInMinutes,
                        trainerFullName = it.trainerFullName
                    )
                }
            } catch(ex: Exception) {
                _viewState.value = TrainingDetailsViewState.Error(ex.message ?: "Неописанная ошибка ${ex::class.java.simpleName}")
            }
        }
    }
}