package com.avangard.bratstvo.intro.about_user

import com.avangard.bratstvo.intro.about_user.data.IntroAboutUserApi
import com.avangard.bratstvo.intro.about_user.data.IntroAboutUserRepositoryImpl
import com.avangard.bratstvo.intro.about_user.data.mapper.HobbiesListDtoMapperImpl
import com.avangard.bratstvo.intro.about_user.data.mapper.HobbiesListUiMapperImpl
import com.avangard.bratstvo.intro.about_user.data.mapper.HobbyDtoMapperImpl
import com.avangard.bratstvo.intro.about_user.data.mapper.HobbyUiMapperImpl
import com.avangard.bratstvo.intro.about_user.data.mapper.IntroFirstStepMapperImpl
import com.avangard.bratstvo.intro.about_user.domain.GetHobbiesUseCase
import com.avangard.bratstvo.intro.about_user.domain.IntroAboutUserRepository
import com.avangard.bratstvo.intro.about_user.domain.SaveHobbiesUseCase
import com.avangard.bratstvo.intro.about_user.domain.mapper.HobbiesListDtoMapper
import com.avangard.bratstvo.intro.about_user.domain.mapper.HobbiesListUiMapper
import com.avangard.bratstvo.intro.about_user.domain.mapper.HobbyDtoMapper
import com.avangard.bratstvo.intro.about_user.domain.mapper.HobbyUiMapper
import com.avangard.bratstvo.intro.about_user.domain.mapper.IntroFirstStepMapper
import com.avangard.bratstvo.intro.about_user.presentation.IntroAboutUserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

internal fun introAboutUserModule() = module {
    factory<IntroAboutUserApi> { get<Retrofit>().create() }

    factory<HobbiesListDtoMapper> { HobbiesListDtoMapperImpl(get()) }
    factory<HobbyDtoMapper> { HobbyDtoMapperImpl() }
    factory<HobbiesListUiMapper> { HobbiesListUiMapperImpl(get()) }
    factory<HobbyUiMapper> { HobbyUiMapperImpl() }
    factory<IntroFirstStepMapper> { IntroFirstStepMapperImpl() }

    factory<IntroAboutUserRepository> { IntroAboutUserRepositoryImpl(get(), get(), get(), get()) }

    factory { GetHobbiesUseCase(get()) }
    factory { SaveHobbiesUseCase(get()) }

    viewModel { IntroAboutUserViewModel(get(), get(), get()) }
}