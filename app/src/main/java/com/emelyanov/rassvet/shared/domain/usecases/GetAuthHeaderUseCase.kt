package com.emelyanov.rassvet.shared.domain.usecases

import com.emelyanov.rassvet.shared.domain.services.rassvetApi.AUTH_PREFIX
import javax.inject.Inject


class GetAuthHeaderUseCase
@Inject
constructor(
    private val getAccessToken: GetAccessTokenUseCase
) {
    operator fun invoke() : String
    = AUTH_PREFIX + getAccessToken()
}