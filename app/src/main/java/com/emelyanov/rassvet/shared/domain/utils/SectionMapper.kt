package com.emelyanov.rassvet.shared.domain.utils

import com.emelyanov.rassvet.shared.domain.models.Section
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