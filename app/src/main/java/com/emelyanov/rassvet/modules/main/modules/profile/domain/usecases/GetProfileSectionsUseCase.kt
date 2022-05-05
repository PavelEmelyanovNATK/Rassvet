package com.emelyanov.rassvet.modules.main.modules.profile.domain.usecases

import com.emelyanov.rassvet.shared.domain.models.Section
import com.emelyanov.rassvet.shared.domain.services.rassvetApi.IRassvetApi
import com.emelyanov.rassvet.shared.domain.usecases.GetAuthHeaderUseCase
import com.emelyanov.rassvet.shared.domain.usecases.ProvideAuthedRequestUseCase
import com.emelyanov.rassvet.shared.domain.utils.SectionMapper
import javax.inject.Inject


class GetProfileSectionsUseCase
@Inject
constructor(
    private val rassvetApi: IRassvetApi,
    private val provideAuthedRequest: ProvideAuthedRequestUseCase,
    private val getAuthHeader: GetAuthHeaderUseCase,
    private val sectionMapper: SectionMapper
) {
    suspend operator fun invoke() : List<Section>
    = provideAuthedRequest {
        rassvetApi.fetchClientSections(
            authHeader = getAuthHeader()
        )
    }?.map {
        sectionMapper.map(it)
    } ?: listOf()
}