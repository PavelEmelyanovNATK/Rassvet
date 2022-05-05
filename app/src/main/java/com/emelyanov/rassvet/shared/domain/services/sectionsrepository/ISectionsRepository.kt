package com.emelyanov.rassvet.shared.domain.services.sectionsrepository

import com.emelyanov.rassvet.shared.domain.models.responseModels.SectionDetailsResponse
import com.emelyanov.rassvet.shared.domain.models.responseModels.SectionResponse

interface ISectionsRepository {
    suspend fun getAllSections(): List<SectionResponse>
    suspend fun getSectionInfo(id: Int): SectionDetailsResponse
}