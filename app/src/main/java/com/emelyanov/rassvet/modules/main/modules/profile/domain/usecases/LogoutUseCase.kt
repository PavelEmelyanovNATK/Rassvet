package com.emelyanov.rassvet.modules.main.modules.profile.domain.usecases

import com.emelyanov.rassvet.shared.domain.services.authorizationservice.IAuthorizationService
import javax.inject.Inject


class LogoutUseCase
@Inject
constructor(
    private val authService: IAuthorizationService
) {
    suspend operator fun invoke() {
        authService.logout()
    }
}