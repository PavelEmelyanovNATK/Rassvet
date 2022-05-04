package com.emelyanov.rassvet.modules.main.domain.usecases

import com.emelyanov.rassvet.navigation.main.MainDestinations
import com.emelyanov.rassvet.navigation.main.MainNavProvider
import javax.inject.Inject


class NavigateToTrainingsUseCase
@Inject
constructor(
    private val mainNavProvider: MainNavProvider
) {
    operator fun invoke() {
        mainNavProvider.navigateTo(MainDestinations.Trainings)
    }
}