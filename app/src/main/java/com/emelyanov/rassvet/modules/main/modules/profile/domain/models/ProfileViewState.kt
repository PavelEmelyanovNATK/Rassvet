package com.emelyanov.rassvet.modules.main.modules.profile.domain.models

import java.util.*

sealed class ProfileViewState {
    object Loading : ProfileViewState()

    data class Authorized(
        val surname: String,
        val name: String,
        val patronymic: String?,
        val birthDate: Date,
        val registrationDate: Date
    ) : ProfileViewState()

    data class Error(val message: String) : ProfileViewState()
}