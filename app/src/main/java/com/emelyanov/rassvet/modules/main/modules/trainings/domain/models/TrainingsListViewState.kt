package com.emelyanov.rassvet.modules.main.modules.trainings.domain.models

import androidx.compose.runtime.MutableState
import com.emelyanov.rassvet.shared.domain.models.Section

sealed class TrainingsListViewState {
    object Loading : TrainingsListViewState()
    data class NoSubscriptions(
        val onSubscriptionsClick: () -> Unit
    ) : TrainingsListViewState()
    data class PresentInfo(
        val pagesCount: Int = 1,
        val onNextPageRequest: () -> Unit = {},
        val sections: List<Section>,
        val selectedSection: MutableState<Section>,
        val onSectionChange: (Section) -> Unit,
        val activeTrainings: Map<Section, List<Training>>,
        val pastTrainings: List<Training>,
        val onTrainingClick: (Int) -> Unit
    ) : TrainingsListViewState()
    data class Error(val message: String) : TrainingsListViewState()
}