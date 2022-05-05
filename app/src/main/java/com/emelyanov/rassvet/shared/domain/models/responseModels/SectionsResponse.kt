package com.emelyanov.rassvet.shared.domain.models.responseModels

import com.google.gson.annotations.SerializedName

data class SectionResponse(
    val id: Int,
    val sectionName: String
)