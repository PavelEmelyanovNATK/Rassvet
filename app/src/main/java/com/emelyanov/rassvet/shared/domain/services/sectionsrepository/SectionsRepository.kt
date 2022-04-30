package com.emelyanov.rassvet.shared.domain.services.sectionsrepository

import com.emelyanov.rassvet.shared.domain.models.responseModels.SectionDetailsResponse
import com.emelyanov.rassvet.shared.domain.models.responseModels.SectionResponse
import com.emelyanov.rassvet.shared.domain.services.rassvetApi.IRassvetApi
import java.net.SocketException
import java.net.SocketTimeoutException

class SectionsRepository
constructor(
    private val rassvetApi: IRassvetApi
) : ISectionsRepository {
    override suspend fun getAllSections(): List<SectionResponse> {
        try{
            val sectionsResponse = rassvetApi.fetchAllSections()

            sectionsResponse.body()?.let {
                return@getAllSections it
            }

            return listOf()
        }
        catch (ex: SocketTimeoutException) {
            throw Exception("Сервер не отвечает...")
        }
        catch(ex: SocketException) {
            throw Exception("Ошибка соединения. Проверте подключение к интернету.")
        }
    }

    override suspend fun getSectionInfo(id: Int): SectionDetailsResponse {
        try{
            val sectionResponse = rassvetApi.fetchSectionDetails(id)

            if(sectionResponse.body() == null)
                throw Exception("Секция не найдена.")

            return sectionResponse.body()!!
        }
        catch (ex: SocketTimeoutException) {
            throw Exception("Сервер не отвечает...")
        }
        catch(ex: SocketException) {
            throw Exception("Ошибка соединения. Проверте подключение к интернету.")
        }
    }
}