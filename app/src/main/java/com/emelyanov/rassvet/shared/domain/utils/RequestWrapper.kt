package com.emelyanov.rassvet.shared.domain.utils

import com.emelyanov.rassvet.shared.domain.models.responseModels.BaseResponse
import retrofit2.Response
import java.net.SocketException
import java.net.SocketTimeoutException
import javax.inject.Inject

suspend inline fun <T> requestWrapper(request: suspend () -> Response<BaseResponse<T>>): T? {
    return try {
        processResponse(request())
    } catch (ex: SocketTimeoutException) {
        throw Exception("Сервер не отвечает...")
    } catch(ex: SocketException) {
        throw Exception("Ошибка соединения. Проверте подключение к интернету.")
    }
}

fun <T> processResponse(response: Response<BaseResponse<T>>): T? {
    when {
        response.body() == null -> throw EmptyBodyException()
        response.body()?.code == 401 -> throw UnauthorizedException()
        response.body()?.code == 200 -> return response.body()!!.data
        else -> throw java.lang.Exception(response.body()?.errors)
    }
}

class EmptyBodyException : java.lang.Exception()
class UnauthorizedException : java.lang.Exception()