package com.avangard.bratstvo.intro.interests

import com.avangard.bratstvo.intro.interests.data.IntroInterestsApi
import com.avangard.bratstvo.intro.interests.data.IntroInterestsRepositoryImpl
import com.avangard.bratstvo.intro.interests.data.mapper.InterestDtoMapperImpl
import com.avangard.bratstvo.intro.interests.data.mapper.InterestUiMapperImpl
import com.avangard.bratstvo.intro.interests.data.mapper.InterestsListDtoMapperImpl
import com.avangard.bratstvo.intro.interests.data.mapper.InterestsListUiMapperImpl
import com.avangard.bratstvo.intro.interests.data.mapper.IntroSecondStepMapperImpl
import com.avangard.bratstvo.intro.interests.domain.GetInterestsUseCase
import com.avangard.bratstvo.intro.interests.domain.IntroInterestsRepository
import com.avangard.bratstvo.intro.interests.domain.SaveInterestsUseCase
import com.avangard.bratstvo.intro.interests.domain.mapper.InterestDtoMapper
import com.avangard.bratstvo.intro.interests.domain.mapper.InterestUiMapper
import com.avangard.bratstvo.intro.interests.domain.mapper.InterestsListDtoMapper
import com.avangard.bratstvo.intro.interests.domain.mapper.InterestsListUiMapper
import com.avangard.bratstvo.intro.interests.domain.mapper.IntroSecondStepMapper
import com.avangard.bratstvo.intro.interests.presentation.IntroInterestsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

internal fun introInterestsModule() = module {
    factory<IntroInterestsApi> { get<Retrofit>().create() }

    factory<InterestsListDtoMapper> { InterestsListDtoMapperImpl(get()) }
    factory<InterestDtoMapper> { InterestDtoMapperImpl() }
    factory<IntroSecondStepMapper> { IntroSecondStepMapperImpl() }
    factory<InterestsListUiMapper> { InterestsListUiMapperImpl(get()) }
    factory<InterestUiMapper> { InterestUiMapperImpl() }

    factory<IntroInterestsRepository> { IntroInterestsRepositoryImpl(get(), get(), get(), get()) }

    factory { GetInterestsUseCase(get()) }
    factory { SaveInterestsUseCase(get()) }

    viewModel { IntroInterestsViewModel(get(), get(), get(), get()) }
}