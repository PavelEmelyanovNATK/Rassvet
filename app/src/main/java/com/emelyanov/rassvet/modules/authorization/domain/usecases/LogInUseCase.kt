package com.emelyanov.rassvet.modules.authorization.domain.usecases

import com.emelyanov.rassvet.shared.domain.models.requestModels.LogInRequest
import com.emelyanov.rassvet.shared.domain.services.authorizationservice.IAuthorizationService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class LogInUseCase
@Inject
constructor(
    private val authService: IAuthorizationService
) {
    suspend operator fun invoke(email: String, password: String) {
        authService.logIn(
            LogInRequest(
                email = email,
                password = password
            )
        )
    }
}