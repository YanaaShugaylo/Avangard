package com.avangard.bratstvo.base.network.data.provider

import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class OkHttpClientBuilderProvider(
    private val interceptors: List<Interceptor?>,
   private val authenticator: Authenticator?
) {
    fun provide(): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            readTimeout(ALL_TIMEOUTS_CONNECTION, TimeUnit.SECONDS)
            connectTimeout(ALL_TIMEOUTS_CONNECTION, TimeUnit.SECONDS)
            writeTimeout(ALL_TIMEOUTS_CONNECTION, TimeUnit.SECONDS)

            interceptors.filterNotNull().forEach { interceptor ->
                addNetworkInterceptor(interceptor)
            }
           if (authenticator != null) {
              authenticator(authenticator)
        }
        }
    }

    companion object {
        private const val ALL_TIMEOUTS_CONNECTION = 30L
    }
}