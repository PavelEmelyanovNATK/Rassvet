package com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.usecases

import com.emelyanov.rassvet.modules.main.modules.profile.domain.models.ProfileViewState
import com.emelyanov.rassvet.shared.domain.models.AuthorizationState
import com.emelyanov.rassvet.shared.domain.services.authorizationservice.IAuthorizationService
import javax.inject.Inject


class GetClientFullNameUseCase
@Inject
constructor(
    private val authService: IAuthorizationService
) {
    operator fun invoke() : String
    = when(val state = authService.authorizationState.value) {
        is AuthorizationState.Authorized -> "${state.surname} ${state.name} ${state.patronymic}"
        else -> throw Exception("Ошибка авторизации")
    }
}