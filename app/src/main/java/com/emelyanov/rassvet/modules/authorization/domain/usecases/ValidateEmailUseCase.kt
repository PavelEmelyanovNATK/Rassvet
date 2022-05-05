package com.emelyanov.rassvet.modules.authorization.domain.usecases

import android.util.Patterns
import com.emelyanov.rassvet.shared.domain.models.ValidationResult
import java.util.regex.Pattern
import javax.inject.Inject

class ValidateEmailUseCase
@Inject
constructor(

) {
    operator fun invoke(email: String) : ValidationResult {
        return when {
            email.isEmpty() -> ValidationResult.Failed("Поле не заполнено.")
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> ValidationResult.Failed("Неверный формат Email.")
            else -> ValidationResult.Success
        }

    }
}