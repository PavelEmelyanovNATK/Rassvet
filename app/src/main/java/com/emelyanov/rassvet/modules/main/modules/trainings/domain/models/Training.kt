package com.emelyanov.rassvet.modules.main.modules.trainings.domain.models

import java.util.*

data class Training(
    val id: Int,
    val title: String,
    val sectionId: Int,
    val startDate: Date,
    val durationInMinutes: Int,
    val trainerFullName: String
)