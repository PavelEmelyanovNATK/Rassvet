package com.emelyanov.rassvet.shared.domain.models.responseModels

data class TokensResponse(
    val accessToken: String,
    val refreshToken: String
)