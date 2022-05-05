package com.emelyanov.rassvet.modules.main.modules.trainings.domain.models

import java.util.*

sealed class TrainingDetailsViewState {
    object Loading : TrainingDetailsViewState()
    data class PresentInfo(
        val title: String,
        val room: String,
        val section: String,
        val startDate: Date,
        val durationInMinutes: Int,
        val description: String,
        val trainerFullName: String
    ) : TrainingDetailsViewState()
    data class Error(val message: String) : TrainingDetailsViewState()
}