package com.emelyanov.rassvet.modules.authorization.domain.usecases

import com.emelyanov.rassvet.shared.domain.models.ValidationResult
import javax.inject.Inject


class ValidateRequiredFieldUseCase
@Inject
constructor(

) {
    operator fun invoke(field: String) : ValidationResult
    = when {
        field.isEmpty() -> ValidationResult.Failed("Поле не заполнено.")
        else -> ValidationResult.Success
    }
}