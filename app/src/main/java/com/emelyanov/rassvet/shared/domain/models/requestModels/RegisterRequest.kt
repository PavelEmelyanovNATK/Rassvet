package com.emelyanov.rassvet.shared.domain.models.requestModels

import java.util.*

data class RegisterRequest(
    val email: String,
    val password: String,
    val confirmPassword: String,
    val surname: String,
    val name: String,
    val patronymic: String,
    val birthDate: Date
)