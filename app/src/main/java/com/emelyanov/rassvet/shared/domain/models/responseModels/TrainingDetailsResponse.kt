package com.emelyanov.rassvet.shared.domain.models.responseModels

class TrainingDetailsResponse (
    val id: Int,
    val title: String,
    val room: String,
    val sectionName: String,
    val description: String,
    val startDate: String,
    val durationInMinutes: Int,
    val trainerFullName: String
)