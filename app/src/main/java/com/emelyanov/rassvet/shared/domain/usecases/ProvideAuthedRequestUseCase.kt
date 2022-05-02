package com.emelyanov.rassvet.shared.domain.usecases

import android.util.Log
import com.emelyanov.rassvet.shared.domain.models.requestModels.RefreshRequest
import com.emelyanov.rassvet.shared.domain.models.responseModels.BaseResponse
import com.emelyanov.rassvet.shared.domain.services.rassvetApi.IRassvetApi
import com.emelyanov.rassvet.shared.domain.utils.EmptyBodyException
import com.emelyanov.rassvet.shared.domain.utils.UnauthorizedException
import com.emelyanov.rassvet.shared.domain.utils.processResponse
import retrofit2.Response
import java.net.SocketException
import java.net.SocketTimeoutException
import javax.inject.Inject

class ProvideAuthedRequestUseCase
@Inject
constructor(
    private val rassvetApi: IRassvetApi,
    private val getRefreshToken: GetRefreshTokenUseCase,
    private val saveTokens: SaveTokensUseCase
) {
    suspend operator fun <T> invoke(request: suspend () -> Response<BaseResponse<T>>) : T? {
        return try {
            val response = request()

            Log.d("Test", "${response.code()}")

            when {
                response.code() == 401 -> {
                    val tokensResponse = rassvetApi.refreshTokens(RefreshRequest(refreshToken = getRefreshToken()))
                    Log.d("Test tokens", "${tokensResponse.code()}")
                    Log.d("Test tokens", "${getRefreshToken()}")
                    if(tokensResponse.code() != 200 || tokensResponse.body()?.code != 200)
                        throw RefreshTokenException()

                    saveTokens(tokensResponse.body()!!.data!!)

                    Log.d("Test tokens", "${getRefreshToken()}")

                    request().body()!!.data
                }
                response.body() == null -> throw EmptyBodyException()
                response.body()?.code == 200 -> return response.body()!!.data
                else -> throw java.lang.Exception(response.body()?.errors)
            }
        } catch (ex: SocketTimeoutException) {
            throw Exception("Сервер не отвечает...")
        } catch(ex: SocketException) {
            throw Exception("Ошибка соединения. Проверте подключение к интернету.")
        }
    }
}

class RefreshTokenException : Exception()