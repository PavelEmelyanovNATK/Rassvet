package com.emelyanov.rassvet.shared.domain.models.responseModels

data class SectionDetailsResponse(
    val id: Int,
    val sectionName: String,
    val description: String,
    val price: Int
)