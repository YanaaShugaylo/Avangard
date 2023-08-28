package com.avangard.bratstvo.base.network.data.provider

import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

class RetrofitProvider(
    private val okHttpClient: OkHttpClient,
    private val converterFactory: Converter.Factory
) {
    fun provide(): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://avangard.htmlup.ru")
            .addConverterFactory(converterFactory)
            .build()
    }
}