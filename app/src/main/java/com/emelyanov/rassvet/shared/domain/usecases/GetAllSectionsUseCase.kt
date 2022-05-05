package com.emelyanov.rassvet.shared.domain.usecases

import com.emelyanov.rassvet.shared.domain.models.responseModels.SectionResponse
import com.emelyanov.rassvet.shared.domain.services.sectionsrepository.FakeSectionsRepository
import com.emelyanov.rassvet.shared.domain.services.sectionsrepository.ISectionsRepository
import javax.inject.Inject

class GetAllSectionsUseCase
@Inject
constructor(
    private val sectionsRepository: ISectionsRepository
) {
    suspend operator fun invoke(): List<SectionResponse> {
        return sectionsRepository.getAllSections()
    }
}