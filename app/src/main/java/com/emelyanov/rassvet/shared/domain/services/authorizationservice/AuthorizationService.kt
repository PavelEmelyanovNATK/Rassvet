package com.emelyanov.rassvet.shared.domain.services.authorizationservice

import com.emelyanov.rassvet.shared.domain.models.AuthorizationState
import com.emelyanov.rassvet.shared.domain.models.requestModels.LogInRequest
import com.emelyanov.rassvet.shared.domain.models.requestModels.RefreshRequest
import com.emelyanov.rassvet.shared.domain.models.requestModels.RegisterRequest
import com.emelyanov.rassvet.shared.domain.services.rassvetApi.AUTH_PREFIX
import com.emelyanov.rassvet.shared.domain.services.rassvetApi.IRassvetApi
import com.emelyanov.rassvet.shared.domain.usecases.*
import com.emelyanov.rassvet.shared.domain.utils.EmptyBodyException
import com.emelyanov.rassvet.shared.domain.utils.requestWrapper
import com.emelyanov.rassvet.shared.domain.utils.toClientInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.Exception

class AuthorizationService
constructor(
    private val rassvetApi: IRassvetApi,
    private val saveTokens: SaveTokensUseCase,
    private val getRefreshToken: GetRefreshTokenUseCase,
    private val getAccessToken: GetAccessTokenUseCase,
    private val provideAuthedRequest: ProvideAuthedRequestUseCase,
    private val clearTokens: ClearTokensUseCase
): IAuthorizationService {

    private val _authorizationState: MutableStateFlow<AuthorizationState>
            = MutableStateFlow(AuthorizationState.Loading)
    override val authorizationState: StateFlow<AuthorizationState>
        get() = _authorizationState

    override suspend fun logIn(logInRequest: LogInRequest) {
        val tokens = requestWrapper {
            rassvetApi.logIn(logInRequest)
        }

        tokens?.let {
            saveTokens(tokens)

            val userInfo = requestWrapper {
                rassvetApi.fetchClientInfo(AUTH_PREFIX + getAccessToken())
            }!!.toClientInfo()

            _authorizationState.tryEmit(
                AuthorizationState.Authorized(
                surname = userInfo.surname,
                name = userInfo.name,
                patronymic = userInfo.patronymic,
                birthDate = userInfo.birthDate,
                registrationDate = userInfo.registrationDate
                )
            )
        }
    }

    override suspend fun register(registerRequest: RegisterRequest) {
        TODO("Not yet implemented")
    }

    override suspend fun authorize() {
        try {
            val userInfo = provideAuthedRequest {
                rassvetApi.fetchClientInfo(AUTH_PREFIX + getAccessToken())
            }!!.toClientInfo()

            _authorizationState.tryEmit(
                AuthorizationState.Authorized(
                    surname = userInfo.surname,
                    name = userInfo.name,
                    patronymic = userInfo.patronymic,
                    birthDate = userInfo.birthDate,
                    registrationDate = userInfo.registrationDate
                )
            )
        } catch (ex: RefreshTokenException) {
            _authorizationState.tryEmit(AuthorizationState.Unauthorized)
        }
    }

    override suspend fun logout() {
        try {
            requestWrapper {
                rassvetApi.logout(RefreshRequest(refreshToken = getRefreshToken()))
            }
        } catch (ex: EmptyBodyException) {}

        clearTokens()
        _authorizationState.tryEmit(AuthorizationState.Unauthorized)
    }
}