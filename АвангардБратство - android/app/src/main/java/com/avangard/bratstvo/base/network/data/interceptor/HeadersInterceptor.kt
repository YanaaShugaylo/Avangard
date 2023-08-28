package com.avangard.bratstvo.base.network.data.interceptor

import com.avangard.bratstvo.authorization.root.domain.GetAuthTokenUseCase
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class HeadersInterceptor(
    private val getAuthToken: GetAuthTokenUseCase
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        return chain.proceed(
            getBuilderWithHeaders(request).build()
        )
    }

    private fun getBuilderWithHeaders(request: Request): Request.Builder {
        return request.newBuilder().apply {
            var authToken = getAuthToken()

            header(FORMAT, FORMAT_JSON_VALUE)

            if (!authToken.isNullOrEmpty()) {
                authToken = AUTHORIZATION_TOKEN_PREFIX + authToken
                header(AUTHORIZATION, authToken)
            }
        }
    }

    companion object {
        private const val FORMAT = "accept"
        private const val FORMAT_JSON_VALUE = "application/json"
        private const val AUTHORIZATION = "Authorization"
        private const val AUTHORIZATION_TOKEN_PREFIX = "Bearer "
    }
}