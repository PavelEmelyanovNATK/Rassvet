package com.emelyanov.rassvet.shared.domain.models.responseModels

data class BaseResponse<T>(
    val code: Int,
    val data: T?,
    val errors: String?
)
