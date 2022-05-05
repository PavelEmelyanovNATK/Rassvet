package com.emelyanov.rassvet.shared.domain.models

import java.util.*

sealed class AuthorizationState {
    object Loading : AuthorizationState()
    object Unauthorized : AuthorizationState()
    data class Authorized(
        val surname: String,
        val name: String,
        val patronymic: String?,
        val birthDate: Date,
        val registrationDate: Date
    ) : AuthorizationState()
}