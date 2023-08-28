package com.avangard.bratstvo.base

import android.content.Context
import com.avangard.bratstvo.authorization.authorizationModule
import com.avangard.bratstvo.authorization.root.domain.AuthorizationLocalRepository
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val context: Context,  var api: AuthorizationLocalRepository?) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
            .also {

                var token = api?.getAuthToken()
                if (!token.isNullOrEmpty()) {
                    it.header("Authorization", "Bearer $token")
                }
            }
            .build()

        return chain.proceed(request)
    }

}