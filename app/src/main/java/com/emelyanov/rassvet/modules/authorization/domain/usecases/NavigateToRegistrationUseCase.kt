package com.emelyanov.rassvet.modules.authorization.domain.usecases

import com.emelyanov.rassvet.navigation.authorization.AuthDestinations
import com.emelyanov.rassvet.navigation.authorization.AuthNavProvider
import javax.inject.Inject


class NavigateToRegistrationUseCase
@Inject
constructor(
    private val authNavProvider: AuthNavProvider
) {
    operator fun invoke() {
        authNavProvider.navigateTo(AuthDestinations.Registration)
    }
}