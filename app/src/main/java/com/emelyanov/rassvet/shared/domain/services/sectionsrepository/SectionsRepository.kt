package com.emelyanov.rassvet.shared.domain.services.sectionsrepository

import com.emelyanov.rassvet.shared.domain.models.responseModels.SectionDetailsResponse
import com.emelyanov.rassvet.shared.domain.models.responseModels.SectionResponse
import com.emelyanov.rassvet.shared.domain.services.rassvetApi.IRassvetApi
import com.emelyanov.rassvet.shared.domain.utils.EmptyBodyException
import com.emelyanov.rassvet.shared.domain.utils.requestWrapper
import java.net.SocketException
import java.net.SocketTimeoutException

class SectionsRepository
constructor(
    private val rassvetApi: IRassvetApi
) : ISectionsRepository {
    override suspend fun getAllSections(): List<SectionResponse> {
        return try {
            requestWrapper {
                rassvetApi.fetchAllSections()
            } ?: listOf()
        } catch(ex: EmptyBodyException) {
            listOf()
        }
    }

    override suspend fun getSectionInfo(id: Int): SectionDetailsResponse {
        return requestWrapper {
            rassvetApi.fetchSectionDetails(id)
        }!!
    }
}