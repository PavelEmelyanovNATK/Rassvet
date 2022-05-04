package com.emelyanov.rassvet.shared.domain.services.rassvetApi

import com.emelyanov.rassvet.shared.domain.models.requestModels.LogInRequest
import com.emelyanov.rassvet.shared.domain.models.requestModels.RefreshRequest
import com.emelyanov.rassvet.shared.domain.models.requestModels.RegisterRequest
import com.emelyanov.rassvet.shared.domain.models.responseModels.*
import retrofit2.Response
import retrofit2.http.*

const val BASE_URL = "http://10.147.17.238:3000"
const val AUTH_PREFIX = "Bearer "

interface IRassvetApi {
    @GET("sections")
    suspend fun fetchAllSections() : Response<BaseResponse<List<SectionResponse>>>

    @GET("sectionDetails/{id}")
    suspend fun fetchSectionDetails(@Path("id") id: Int) : Response<BaseResponse<SectionDetailsResponse>>

    @POST("auth/login")
    suspend fun logIn(@Body logInRequest: LogInRequest) : Response<BaseResponse<TokensResponse>>

    @POST("auth/register/client")
    suspend fun register(@Body registerRequest: RegisterRequest) : Response<BaseResponse<Unit>>

    @GET("me/info")
    suspend fun fetchClientInfo(
        @Header("Authorization") authHeader: String
    ) : Response<BaseResponse<ClientInfoResponse>>

    @POST("auth/refresh")
    suspend fun refreshTokens(@Body refreshRequest: RefreshRequest) : Response<BaseResponse<TokensResponse>>

    @POST("auth/logout")
    suspend fun logout(@Body refreshRequest: RefreshRequest) : Response<BaseResponse<Unit>>

    @GET("me/active-trainings")
    suspend fun fetchClientActiveTrainings(
        @Header("Authorization") authHeader: String
    ) : Response<BaseResponse<List<TrainingResponse>>>

    @GET("me/active-trainings/{sectionId}")
    suspend fun fetchClientActiveTrainingsBySection(
        @Header("Authorization") authHeader: String,
        @Path("sectionId") sectionId: Int
    ) : Response<BaseResponse<List<TrainingResponse>>>

    @GET("me/past-trainings/{pagesCount}")
    suspend fun fetchClientPastTrainings(
        @Header("Authorization") authHeader: String,
        @Path("pagesCount") pagesCount: Int,
    ) : Response<BaseResponse<List<TrainingResponse>>>

    @GET("me/past-training/{sectionId}/{pagesCount}")
    suspend fun fetchClientPastTrainingsBySection(
        @Header("Authorization") authHeader: String,
        @Path("sectionId") sectionId: Int,
        @Path("pagesCount") pagesCount: Int
    ) : Response<BaseResponse<List<TrainingResponse>>>

    @GET("me/sections")
    suspend fun fetchClientSections(
        @Header("Authorization") authHeader: String
    ) : Response<BaseResponse<List<SectionResponse>>>

    @GET("me/training-details/{id}")
    suspend fun fetchTrainingDetails(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int
    ) : Response<BaseResponse<TrainingDetailsResponse>>
}