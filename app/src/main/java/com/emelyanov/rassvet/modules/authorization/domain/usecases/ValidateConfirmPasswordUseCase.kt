package com.emelyanov.rassvet.modules.authorization.domain.usecases

import com.emelyanov.rassvet.shared.domain.models.ValidationResult
import javax.inject.Inject


class ValidateConfirmPasswordUseCase
@Inject
constructor(

) {
    operator fun invoke(password: String, confirmPassword: String) : ValidationResult
    = when {

        password != confirmPassword -> ValidationResult.Failed("Пароли не совпадают.")
        else -> ValidationResult.Success
    }
}