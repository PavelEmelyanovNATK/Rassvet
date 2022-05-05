package com.emelyanov.rassvet.shared.domain.models

sealed class ValidationResult {
    object Success : ValidationResult()
    data class Failed(val message: String) : ValidationResult()
}