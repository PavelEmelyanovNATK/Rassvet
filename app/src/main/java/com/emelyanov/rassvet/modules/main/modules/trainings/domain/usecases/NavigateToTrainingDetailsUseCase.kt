package com.emelyanov.rassvet.modules.main.modules.trainings.domain.usecases

import com.emelyanov.rassvet.navigation.trainings.TrainingsDestinations
import com.emelyanov.rassvet.navigation.trainings.TrainingsNavProvider
import javax.inject.Inject


class NavigateToTrainingDetailsUseCase
@Inject
constructor(
    private val trainingsNavProvider: TrainingsNavProvider
) {
    operator fun invoke(id: Int) {
        trainingsNavProvider.navigateTo(TrainingsDestinations.TrainingDetails(id))
    }
}