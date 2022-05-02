package com.emelyanov.rassvet.shared.domain.models.responseModels

data class ClientInfoResponse(
    val surname: String,
    val name: String,
    val patronymic: String?,
    val birthDate: String,
    val registrationDate: String
)
