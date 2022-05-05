package com.emelyanov.rassvet.shared.domain.models.responseModels

data class ClientSectionDetailsResponse(
    val id: Int,
    val sectionName: String,
    val description: String,
    val price: Int,
    val isSubscribed: Boolean
)