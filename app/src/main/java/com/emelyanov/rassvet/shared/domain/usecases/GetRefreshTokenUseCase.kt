package com.emelyanov.rassvet.shared.domain.usecases

import com.emelyanov.rassvet.shared.domain.services.tokenstorage.ITokensStorage
import com.emelyanov.rassvet.shared.domain.services.tokenstorage.TokensStorage
import javax.inject.Inject

class GetRefreshTokenUseCase
@Inject
constructor(
    private val tokensStorage: ITokensStorage
) {
    operator fun invoke(): String {
        return tokensStorage.refreshToken
    }
}