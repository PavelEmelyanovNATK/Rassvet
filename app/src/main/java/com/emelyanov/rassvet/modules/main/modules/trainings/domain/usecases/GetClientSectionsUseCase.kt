package com.emelyanov.rassvet.modules.main.modules.trainings.domain.usecases

import com.emelyanov.rassvet.modules.main.modules.trainings.domain.models.Section
import com.emelyanov.rassvet.modules.main.modules.trainings.domain.utils.SectionMapper
import com.emelyanov.rassvet.shared.domain.services.rassvetApi.IRassvetApi
import com.emelyanov.rassvet.shared.domain.usecases.GetAccessTokenUseCase
import com.emelyanov.rassvet.shared.domain.usecases.GetAuthHeaderUseCase
import com.emelyanov.rassvet.shared.domain.usecases.ProvideAuthedRequestUseCase
import javax.inject.Inject


class GetClientSectionsUseCase
@Inject
constructor(
    private val rassvetApi: IRassvetApi,
    private val sectionMapper: SectionMapper,
    private val getAuthHeader: GetAuthHeaderUseCase,
    private val provideAuthedRequest: ProvideAuthedRequestUseCase
) {
    suspend operator fun invoke() : List<Section>
    = listOf(Section(id = -1, title = "Любая")).plus(
        provideAuthedRequest{
            rassvetApi.fetchClientSections(
                getAuthHeader()
            )
        }?.map {
            sectionMapper.map(it)
        } ?: listOf()
    )
}