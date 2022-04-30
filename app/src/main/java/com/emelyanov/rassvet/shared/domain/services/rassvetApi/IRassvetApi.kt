package com.emelyanov.rassvet.shared.domain.services.rassvetApi

import com.emelyanov.rassvet.shared.domain.models.responseModels.SectionDetailsResponse
import com.emelyanov.rassvet.shared.domain.models.responseModels.SectionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IRassvetApi {
    @GET("sections")
    suspend fun fetchAllSections() : Response<List<SectionResponse>>

    @GET("sectionDetails/{id}")
    suspend fun fetchSectionDetails(@Path("id") id: Int) : Response<SectionDetailsResponse>
}