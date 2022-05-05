package com.emelyanov.rassvet.modules.main.modules.trainings.domain.usecases

import com.emelyanov.rassvet.navigation.trainings.TrainingsDestinations
import com.emelyanov.rassvet.navigation.trainings.TrainingsNavProvider
import javax.inject.Inject


class PopBackFromDetailsUseCase
@Inject
constructor(
    private val trainingsNavProvider: TrainingsNavProvider
) {
    operator fun invoke() {
        trainingsNavProvider.navigateTo(TrainingsDestinations.PopBack)
    }
}