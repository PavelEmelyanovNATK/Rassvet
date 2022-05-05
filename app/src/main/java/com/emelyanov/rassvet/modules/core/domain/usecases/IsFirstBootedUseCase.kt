package com.emelyanov.rassvet.modules.core.domain.usecases

import com.emelyanov.rassvet.shared.domain.services.tokenstorage.ITokensStorage
import com.emelyanov.rassvet.shared.domain.services.tokenstorage.TokensStorage
import javax.inject.Inject

class IsFirstBootedUseCase
@Inject
constructor(
    private val tokensStorage: ITokensStorage
) {
    operator fun invoke(): Boolean {
        return !tokensStorage.areTokensExists
    }
}