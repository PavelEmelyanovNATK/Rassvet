package com.emelyanov.rassvet.modules.firstboot.domain.usecases

import com.emelyanov.rassvet.shared.domain.models.responseModels.SectionDetailsResponse
import com.emelyanov.rassvet.shared.domain.services.sectionsrepository.FakeSectionsRepository
import com.emelyanov.rassvet.shared.domain.services.sectionsrepository.ISectionsRepository
import javax.inject.Inject

class GetSectionInfoUseCase
@Inject
constructor(
    private val sectionsRepository: ISectionsRepository
) {
    suspend operator fun invoke(id: Int): SectionDetailsResponse {
        return sectionsRepository.getSectionInfo(id)
    }
}