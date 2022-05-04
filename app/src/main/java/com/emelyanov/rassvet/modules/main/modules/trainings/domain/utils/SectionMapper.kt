package com.emelyanov.rassvet.modules.main.modules.trainings.domain.utils

import com.emelyanov.rassvet.modules.main.modules.trainings.domain.models.Section
import com.emelyanov.rassvet.shared.domain.models.responseModels.SectionResponse
import javax.inject.Inject


class SectionMapper
@Inject
constructor(

) {
    fun map(sectionResponse: SectionResponse) : Section
    = sectionResponse.let {
        Section(
            id = it.id,
            title = it.sectionName
        )
    }
}