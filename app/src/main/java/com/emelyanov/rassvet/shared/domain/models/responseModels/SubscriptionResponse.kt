package com.emelyanov.rassvet.shared.domain.models.responseModels

data class SubscriptionResponse(
    val id: Int,
    val sectionName: String,
    val barcodeString: String,
    val startDate: String,
    val expirationDate: String
)