package com.emelyanov.rassvet.modules.authorization.domain.usecases

import com.emelyanov.rassvet.shared.domain.models.ValidationResult
import javax.inject.Inject


class ValidatePasswordUseCase
@Inject
constructor(

) {
    operator fun invoke(password: String) : ValidationResult
    = when {
        password.length < 6 -> ValidationResult.Failed("Пароль должен содержать больше 5 символов.")
        !password.any { it.isDigit() } -> ValidationResult.Failed("Пароль должен содержать цифры.")
        !password.any { it.isUpperCase() } -> ValidationResult.Failed("Пароль должен содержать заглавную букву.")
        !password.any { it.isLowerCase() } -> ValidationResult.Failed("Пароль должен содержать прописную букву.")
        else -> ValidationResult.Success
    }
}