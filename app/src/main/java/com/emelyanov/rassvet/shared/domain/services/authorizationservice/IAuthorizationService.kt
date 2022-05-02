package com.emelyanov.rassvet.shared.domain.services.authorizationservice

import com.emelyanov.rassvet.shared.domain.models.AuthorizationState
import com.emelyanov.rassvet.shared.domain.models.requestModels.LogInRequest
import com.emelyanov.rassvet.shared.domain.models.requestModels.RegisterRequest
import kotlinx.coroutines.flow.StateFlow

interface IAuthorizationService {
    val authorizationState: StateFlow<AuthorizationState>

    suspend fun logIn(logInRequest: LogInRequest)

    suspend fun register(registerRequest: RegisterRequest)

    suspend fun authorize()

    suspend fun logout()
}