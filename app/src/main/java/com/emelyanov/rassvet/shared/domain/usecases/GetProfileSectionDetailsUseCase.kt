package com.emelyanov.rassvet.shared.domain.usecases

import com.emelyanov.rassvet.shared.domain.models.responseModels.ClientSectionDetailsResponse
import com.emelyanov.rassvet.shared.domain.models.responseModels.SectionDetailsResponse
import com.emelyanov.rassvet.shared.domain.services.rassvetApi.IRassvetApi
import com.emelyanov.rassvet.shared.domain.usecases.GetAuthHeaderUseCase
import com.emelyanov.rassvet.shared.domain.usecases.ProvideAuthedRequestUseCase
import javax.inject.Inject


class GetProfileSectionDetailsUseCase
@Inject
constructor(
    private val rassvetApi: IRassvetApi,
    private val provideAuthedRequest: ProvideAuthedRequestUseCase,
    private val getAuthHeader: GetAuthHeaderUseCase
) {
    suspend operator fun invoke(id: Int) : ClientSectionDetailsResponse
    = provideAuthedRequest {
        rassvetApi.fetchClientSectionDetails(
            authHeader = getAuthHeader(),
            id = id
        )
    }!!
}