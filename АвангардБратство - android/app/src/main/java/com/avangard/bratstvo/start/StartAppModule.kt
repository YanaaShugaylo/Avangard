package com.avangard.bratstvo.start

import com.avangard.bratstvo.BuildConfig
import com.avangard.bratstvo.base.ApiLogger
import com.avangard.bratstvo.base.TokenInterceptor
import com.avangard.bratstvo.base.network.data.interceptor.ErrorInterceptor
import com.avangard.bratstvo.base.network.data.interceptor.HeadersInterceptor
import com.avangard.bratstvo.start.presentation.StartViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

fun startAppModule() = module {

    viewModel { StartViewModel(get(), get(), get()) }

    factory { ErrorInterceptor(get()) } bind Interceptor::class

    single {
        OkHttpClient.Builder()
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
           .addInterceptor(HeadersInterceptor(get()))
            .addInterceptor(TokenInterceptor(androidContext(), get()))
            .also {
                if (BuildConfig.DEBUG)
                    it.addInterceptor(
                        HttpLoggingInterceptor(ApiLogger()).setLevel(
                            HttpLoggingInterceptor.Level.BODY))
            }
            .build()

    }

}