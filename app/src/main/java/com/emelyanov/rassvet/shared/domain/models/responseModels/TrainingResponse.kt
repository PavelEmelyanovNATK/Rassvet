package com.emelyanov.rassvet.shared.domain.models.responseModels

data class TrainingResponse(
    val id: Int,
    val title: String,
    val sectionId: Int,
    val groupName: String,
    val startDate: String,
    val durationInMinutes: Int,
    val trainerFullName: String
)