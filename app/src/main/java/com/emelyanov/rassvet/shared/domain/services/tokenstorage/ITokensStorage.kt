package com.emelyanov.rassvet.shared.domain.services.tokenstorage

interface ITokensStorage {
    var accessToken: String
    var refreshToken: String
    val areTokensExists: Boolean
}