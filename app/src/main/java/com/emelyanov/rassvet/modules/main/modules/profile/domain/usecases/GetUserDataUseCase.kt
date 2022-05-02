package com.emelyanov.rassvet.modules.main.modules.profile.domain.usecases

import com.emelyanov.rassvet.shared.domain.models.AuthorizationState
import com.emelyanov.rassvet.shared.domain.models.ClientInfo
import com.emelyanov.rassvet.shared.domain.services.authorizationservice.IAuthorizationService
import javax.inject.Inject


class GetUserDataUseCase
@Inject
constructor(
    private val authService: IAuthorizationService
) {
    operator fun invoke() : ClientInfo {
        return when(val state = authService.authorizationState.value) {
            is AuthorizationState.Authorized -> ClientInfo(
                surname = state.surname,
                name = state.name,
                patronymic = state.patronymic,
                birthDate = state.birthDate,
                registrationDate = state.registrationDate
            )
            else -> throw Exception("Пользователь не авторизован!")
        }
    }
}