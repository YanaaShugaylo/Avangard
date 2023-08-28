package com.avangard.bratstvo.profile.personal_data

import com.avangard.bratstvo.profile.personal_data.data.PersonalDataApi
import com.avangard.bratstvo.profile.personal_data.data.PersonalDataRepositoryImpl
import com.avangard.bratstvo.profile.personal_data.data.mapper.PersonalDataDtoMapperImpl
import com.avangard.bratstvo.profile.personal_data.data.mapper.PersonalDataUiMapperImpl
import com.avangard.bratstvo.profile.personal_data.domain.GetPersonalDataUseCase
import com.avangard.bratstvo.profile.personal_data.domain.LogoutUserUseCase
import com.avangard.bratstvo.profile.personal_data.domain.PersonalDataRepository
import com.avangard.bratstvo.profile.personal_data.domain.mapper.PersonalDataUiMapper
import com.avangard.bratstvo.profile.personal_data.domain.mapper.PersonalDataDtoMapper
import com.avangard.bratstvo.profile.personal_data.presentation.ProfilePersonalDataViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

internal fun profilePersonalDataModule() = module {
    factory<PersonalDataApi> { get<Retrofit>().create() }

    factory<PersonalDataDtoMapper> { PersonalDataDtoMapperImpl(get(), get()) }
    factory<PersonalDataUiMapper> { PersonalDataUiMapperImpl() }

    factory<PersonalDataRepository> { PersonalDataRepositoryImpl(get(), get(), get()) }

    factory { GetPersonalDataUseCase(get()) }
    factory { LogoutUserUseCase(get()) }

    viewModel { ProfilePersonalDataViewModel(get(), get(), get()) }
}