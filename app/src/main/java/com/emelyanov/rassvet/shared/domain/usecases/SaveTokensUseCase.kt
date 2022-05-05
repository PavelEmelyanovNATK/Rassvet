package com.emelyanov.rassvet.shared.domain.usecases

import com.emelyanov.rassvet.shared.domain.models.responseModels.TokensResponse
import com.emelyanov.rassvet.shared.domain.services.tokenstorage.ITokensStorage
import com.emelyanov.rassvet.shared.domain.services.tokenstorage.TokensStorage
import javax.inject.Inject

class SaveTokensUseCase
@Inject
constructor(
    private val tokensStorage: ITokensStorage
) {
    operator fun invoke(tokens: TokensResponse) {
        tokensStorage.accessToken = tokens.accessToken
        tokensStorage.refreshToken = tokens.refreshToken
    }
}