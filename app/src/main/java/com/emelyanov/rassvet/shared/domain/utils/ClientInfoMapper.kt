package com.emelyanov.rassvet.shared.domain.utils

import com.emelyanov.rassvet.shared.domain.models.responseModels.ClientInfo
import com.emelyanov.rassvet.shared.domain.models.responseModels.ClientInfoResponse

fun ClientInfoResponse.toClientInfo(): ClientInfo
    = ClientInfo(
        surname = this.surname,
        name = this.name,
        patronymic = this.patronymic,
        birthDate = getDateFromString(this.birthDate),
        registrationDate = getDateFromString(this.registrationDate)
    )
