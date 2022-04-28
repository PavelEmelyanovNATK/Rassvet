package com.emelyanov.rassvet.modules.main.modules.trainings.domain.models

sealed class TrainingsListViewState {
    object Loading : TrainingsListViewState()
    object NoSubscriptions : TrainingsListViewState()
    data class PresentInfo(
        val sections: List<Int>,
        val selectedSection: Int,
        val activeTrainings: Map<Int, List<Int>>,
        val pastTrainings: List<Int>,
        val onTrainingClick: (Int) -> Unit
    ) : TrainingsListViewState()
    data class Error(val message: String) : TrainingsListViewState()
}