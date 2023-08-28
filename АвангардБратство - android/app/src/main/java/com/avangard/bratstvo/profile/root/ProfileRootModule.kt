package com.avangard.bratstvo.profile.root

import com.avangard.bratstvo.profile.root.data.*
import com.avangard.bratstvo.profile.root.data.mapper.UserRatingDtoMapperImpl
import com.avangard.bratstvo.profile.root.data.mapper.UserRatingUiMapperImpl
import com.avangard.bratstvo.profile.root.data.mapper.UserSkillDtoMapperImpl
import com.avangard.bratstvo.profile.root.data.mapper.UserSkillUiMapperImpl
import com.avangard.bratstvo.profile.root.data.mapper.UserSkillsListDtoMapperImpl
import com.avangard.bratstvo.profile.root.domain.GetUserRatingUseCase
import com.avangard.bratstvo.profile.root.domain.GetUserSkillsUseCase
import com.avangard.bratstvo.profile.root.domain.ProfileRepository
import com.avangard.bratstvo.profile.root.domain.mapper.UserRatingDtoMapper
import com.avangard.bratstvo.profile.root.domain.mapper.UserRatingUiMapper
import com.avangard.bratstvo.profile.root.domain.mapper.UserSkillDtoMapper
import com.avangard.bratstvo.profile.root.domain.mapper.UserSkillUiMapper
import com.avangard.bratstvo.profile.root.domain.mapper.UserSkillsListDtoMapper
import com.avangard.bratstvo.profile.root.presentation.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

internal fun profileRootModule() = module {
    factory<ProfileApi> { get<Retrofit>().create() }

    factory<UserRatingDtoMapper> { UserRatingDtoMapperImpl() }
    factory<UserNewBalanceDtoMapper> { UserNewBalanceDtoMapperImpl() }
    factory<UserHistoryDtoMapper> { UserHistoryDtoMapperImpl() }
    factory<UserSkillsListDtoMapper> { UserSkillsListDtoMapperImpl(get()) }
    factory<UserSkillDtoMapper> { UserSkillDtoMapperImpl() }
    factory<UserRatingUiMapper> { UserRatingUiMapperImpl() }
    factory<UserHistoryUiMapper> { UserHistoryUiMapperImpl() }
    factory<UserNewBalanceUiMapper> { UserNewBalanceUiMapperImpl() }
    factory<UserSkillUiMapper> { UserSkillUiMapperImpl() }

    factory<ProfileRepository> { ProfileRepositoryImpl(get(), get(), get(), get(), get(), get()) }

    factory { GetUserRatingUseCase(get()) }
    factory { GetUserSkillsUseCase(get()) }
    factory { GetUserNewBalanceUseCase(get()) }
    factory { GetUserHistoryUseCase(get()) }


    viewModel { ProfileViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get()) }
}