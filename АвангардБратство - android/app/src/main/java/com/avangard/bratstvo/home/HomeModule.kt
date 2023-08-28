package com.avangard.bratstvo.home

import com.avangard.bratstvo.home.data.mapper.HomeItemMapperImpl
import com.avangard.bratstvo.home.domain.HasInternetUseCase
import com.avangard.bratstvo.home.domain.mapper.HomeItemMapper
import com.avangard.bratstvo.home.presentation.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun homeModule() = module {
    factory<HomeItemMapper> { HomeItemMapperImpl() }

    factory { HasInternetUseCase(androidContext()) }

    viewModel { HomeViewModel(get(), get(), get(), get(), get()) }
}