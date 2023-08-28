package com.avangard.bratstvo.base.network.data.interceptor

import com.avangard.bratstvo.base.exceptions.domain.AuthFailedException
import com.avangard.bratstvo.base.exceptions.domain.ClientException
import com.avangard.bratstvo.base.exceptions.domain.ConnectionToServerException
import com.avangard.bratstvo.base.exceptions.domain.BaseException
import com.avangard.bratstvo.base.exceptions.domain.NoInternetException
import com.avangard.bratstvo.base.exceptions.domain.ServerException
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException
import kotlin.reflect.KClass

open class ErrorInterceptor(val gson: Gson) : Interceptor {

    @Throws(IOException::class) // из Interceptor-а можно выбрасывать только IOException, иначе будет краш
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = runCatching {
            chain.proceed(chain.request())
        }.getOrElse { e ->
            throw getMappedException(e)
        }

        return when (response.code) {
            HTTP_STATUS_UNAUTHORIZED_CODE, HTTP_STATUS_AUTHENTICATION_ERROR_CODE -> {
                throw AuthFailedException(response.toHttpException())
            }
            in HTTP_STATUS_CLIENT_ERROR_FIRST..HTTP_STATUS_CLIENT_ERROR_LAST -> {
                throw getClientException(response)
            }
            in HTTP_STATUS_SERVER_ERROR_FIRST..HTTP_STATUS_SERVER_ERROR_LAST -> {
                throw getServerException(response)
            }
            else -> response
        }
    }

    protected open fun getServerException(response: Response): IOException {
        return ServerException(response.toHttpException())
    }

    protected open fun getClientException(response: Response): IOException {
        return ClientException(response.toHttpException())
    }

    protected fun Response.toHttpException(): HttpException {
        return HttpException(toRetrofitError())
    }

    protected open fun getMappedException(e: Throwable): IOException {
        return errorMapping.keys.find { throwableClass ->
            throwableClass.isInstance(e)
        }?.let { key ->
            val newExceptionFunction = errorMapping[key]
            if (newExceptionFunction != null) {
                newExceptionFunction(e)
            } else {
                e.asIOException()
            }
        } ?: e.asIOException()
    }

    private fun Response.toRetrofitError(): retrofit2.Response<String> {
        (body ?: "".toResponseBody()).use { body ->
            return retrofit2.Response.error(body, this)
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun Throwable.asIOException() = (this as? IOException) ?: IOException(this)

    companion object {
        private const val HTTP_STATUS_UNAUTHORIZED_CODE = HttpURLConnection.HTTP_UNAUTHORIZED
        private const val HTTP_STATUS_AUTHENTICATION_ERROR_CODE = 422
        private const val HTTP_STATUS_CLIENT_ERROR_FIRST = HttpURLConnection.HTTP_BAD_REQUEST
        private const val HTTP_STATUS_CLIENT_ERROR_LAST = 499
        private const val HTTP_STATUS_SERVER_ERROR_FIRST = HttpURLConnection.HTTP_INTERNAL_ERROR
        private const val HTTP_STATUS_SERVER_ERROR_LAST = 599

        private val errorMapping = mapOf<KClass<out Throwable>, (cause: Throwable) -> IOException>(
            UnknownHostException::class to { cause -> NoInternetException(cause) },
            SocketTimeoutException::class to { cause -> ConnectionToServerException(cause) },
            JsonSyntaxException::class to { cause -> BaseException(cause) },
            SSLException::class to { cause -> ConnectionToServerException(cause) },
            SocketException::class to { cause -> ConnectionToServerException(cause) }
        )
    }
}