package com.avangard.bratstvo.base

import androidx.preference.PreferenceManager
import com.avangard.bratstvo.authorization.root.data.AuthLocalDataSource
import com.avangard.bratstvo.base.coroutines.AppDispatchers
import com.avangard.bratstvo.base.root.data.BaseRequestsApi
import com.avangard.bratstvo.base.root.data.BaseRequestsRepositoryImpl
import com.avangard.bratstvo.base.root.domain.BaseRequestsRepository
import com.avangard.bratstvo.base.root.domain.UploadFileUseCase
import com.avangard.bratstvo.base.root.presentation.MainViewModel
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

fun baseModule() = module {
    single { AppDispatchers() }

    single {
        GsonBuilder()/*.apply {
            getAll<TypeSerializer>().forEach {
                registerTypeAdapter(it.typeClass, it.serializer)
            }
            getAll<TypeDeserializer>().forEach {
                registerTypeAdapter(it.typeClass, it.deserializer)
            }
        }*/.create()
    }

    single { AuthLocalDataSource(PreferenceManager.getDefaultSharedPreferences(androidContext())) }

    factory<BaseRequestsApi> { get<Retrofit>().create() }

    factory<BaseRequestsRepository> { BaseRequestsRepositoryImpl(get(), get(), androidContext()) }

    factory { UploadFileUseCase(get()) }

    viewModel { MainViewModel(getUser = get(), isUserAuthorized = get(), hasInternet = get()) }
}