package com.emelyanov.rassvet.shared.domain.services.sectionsrepository

import com.emelyanov.rassvet.shared.domain.models.responseModels.SectionDetailsResponse
import com.emelyanov.rassvet.shared.domain.models.responseModels.SectionResponse

class FakeSectionsRepository
    : ISectionsRepository {
    override suspend fun getAllSections(): List<SectionResponse> {
        return listOf(
            SectionResponse(
                id = 1,
                sectionName = "Football"
            ),
            SectionResponse(
                id = 2,
                sectionName = "Basketball"
            ),
            SectionResponse(
                id = 3,
                sectionName = "Volleyball"
            )
        )
    }

    override suspend fun getSectionInfo(id: Int): SectionDetailsResponse {
        return SectionDetailsResponse(
            id = id,
            sectionName = "Section $id",
            description = "Description ".repeat(100),
            price = 1990 + id
        )
    }
}