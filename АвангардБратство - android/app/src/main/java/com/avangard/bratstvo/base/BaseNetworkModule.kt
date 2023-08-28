package com.avangard.bratstvo.base

import com.avangard.bratstvo.BuildConfig
import com.avangard.bratstvo.base.network.data.interceptor.ErrorInterceptor
import com.avangard.bratstvo.base.network.data.interceptor.HeadersInterceptor
import com.avangard.bratstvo.base.network.data.provider.OkHttpClientBuilderProvider
import com.avangard.bratstvo.base.network.data.provider.RetrofitProvider
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

fun baseNetworkModule() = module {
    factory { HeadersInterceptor(get()) } bind Interceptor::class
    factory { ErrorInterceptor(get()) } bind Interceptor::class
    factory { TokenInterceptor(get(), get()) } bind Interceptor::class

    factory {
        HttpLoggingInterceptor(ApiLogger()).apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        }
    } bind Interceptor::class

    single<Converter.Factory> { GsonConverterFactory.create(get()) }
    single { OkHttpClientBuilderProvider(getAll(), get()).provide() }
    single { get<OkHttpClient.Builder>().build() }
    single { RetrofitProvider(get(), get()).provide() }
}