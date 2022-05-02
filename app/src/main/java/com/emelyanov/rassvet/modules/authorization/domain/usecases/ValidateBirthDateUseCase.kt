package com.emelyanov.rassvet.modules.authorization.domain.usecases

import com.emelyanov.rassvet.shared.domain.models.ValidationResult
import java.util.*
import javax.inject.Inject


class ValidateBirthDateUseCase
@Inject
constructor(

) {
    operator fun invoke(date: Date?) : ValidationResult
    = when (date) {
        null -> ValidationResult.Failed("Поле не заполнено")
        else -> ValidationResult.Success
    }
}