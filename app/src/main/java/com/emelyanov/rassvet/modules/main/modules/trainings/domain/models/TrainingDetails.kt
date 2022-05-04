package com.emelyanov.rassvet.modules.main.modules.trainings.domain.models

import java.util.*

data class TrainingDetails(
    val id: Int,
    val title: String,
    val room: String,
    val section: String,
    val description: String,
    val startDate: Date,
    val durationInMinutes: Int,
    val trainerFullName: String
)