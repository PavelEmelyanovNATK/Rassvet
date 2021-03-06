package com.emelyanov.rassvet.shared.domain.models

import java.util.*

data class ClientInfo(
    val surname: String,
    val name: String,
    val patronymic: String?,
    val birthDate: Date,
    val registrationDate: Date
)