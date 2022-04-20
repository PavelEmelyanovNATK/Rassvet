package com.emelyanov.rassvet.shared.domain.models.requestModels

data class LogInRequest(
    val email: String,
    val password: String
)