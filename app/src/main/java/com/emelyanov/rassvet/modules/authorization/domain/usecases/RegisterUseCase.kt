package com.emelyanov.rassvet.modules.authorization.domain.usecases

import com.emelyanov.rassvet.shared.domain.models.requestModels.RegisterRequest
import com.emelyanov.rassvet.shared.domain.services.authorizationservice.IAuthorizationService
import com.emelyanov.rassvet.shared.domain.utils.formatJsonDate
import java.util.*
import javax.inject.Inject


class RegisterUseCase
@Inject
constructor(
    private val authService: IAuthorizationService
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        confirmPassword: String,
        surname: String,
        name: String,
        patronymic: String?,
        birthDate: Date
    ) {
        authService.register(
            RegisterRequest(
                email = email,
                password = password,
                confirmPassword = confirmPassword,
                surname = surname,
                name = name,
                patronymic = patronymic,
                birthDate = birthDate.formatJsonDate()
            )
        )
    }
}