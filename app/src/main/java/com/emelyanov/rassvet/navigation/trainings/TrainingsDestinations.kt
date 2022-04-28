package com.emelyanov.rassvet.navigation.trainings

import com.emelyanov.rassvet.navigation.main.MainDestinations

sealed class TrainingsDestinations(val route: String) {
    object TrainingsList : TrainingsDestinations("${MainDestinations.Trainings.route}/list")
    data class TrainingDetails(val id: Int) : TrainingsDestinations("${MainDestinations.Trainings.route}/details/$id")
    object TrainingDetailsCompRoute : TrainingsDestinations("${MainDestinations.Trainings.route}/details/{id}")
    object PopBack : TrainingsDestinations("")
}