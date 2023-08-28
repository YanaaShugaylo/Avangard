package com.avangard.bratstvo.user

import com.avangard.bratstvo.authorization.root.domain.GetAuthTokenUseCase
import com.avangard.bratstvo.user.data.UserApi
import com.avangard.bratstvo.user.data.UserRepositoryImpl
import com.avangard.bratstvo.user.data.mapper.HobbiesSimpleListDtoMapperImpl
import com.avangard.bratstvo.user.data.mapper.HobbySimpleDtoMapperImpl
import com.avangard.bratstvo.user.data.mapper.InterestSimpleDtoMapperImpl
import com.avangard.bratstvo.user.data.mapper.InterestsSimpleListDtoMapperImpl
import com.avangard.bratstvo.user.data.mapper.UserBalanceDtoMapperImpl
import com.avangard.bratstvo.user.data.mapper.UserDtoMapperImpl
import com.avangard.bratstvo.user.domain.GetUserBalanceUseCase
import com.avangard.bratstvo.user.domain.GetUserUseCase
import com.avangard.bratstvo.user.domain.SaveLocalUserUseCase
import com.avangard.bratstvo.user.domain.UserRepository
import com.avangard.bratstvo.user.domain.mapper.HobbiesSimpleListDtoMapper
import com.avangard.bratstvo.user.domain.mapper.HobbySimpleDtoMapper
import com.avangard.bratstvo.user.domain.mapper.InterestSimpleDtoMapper
import com.avangard.bratstvo.user.domain.mapper.InterestsSimpleListDtoMapper
import com.avangard.bratstvo.user.domain.mapper.UserBalanceDtoMapper
import com.avangard.bratstvo.user.domain.mapper.UserDtoMapper
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

fun userModule() = module {
    factory<UserApi> { get<Retrofit>().create() }

    factory<UserDtoMapper> { UserDtoMapperImpl(get(), get()) }
    factory<UserBalanceDtoMapper> { UserBalanceDtoMapperImpl() }
    factory<HobbiesSimpleListDtoMapper> { HobbiesSimpleListDtoMapperImpl(get()) }
    factory<HobbySimpleDtoMapper> { HobbySimpleDtoMapperImpl() }
    factory<InterestsSimpleListDtoMapper> { InterestsSimpleListDtoMapperImpl(get()) }
    factory<InterestSimpleDtoMapper> { InterestSimpleDtoMapperImpl() }

    factory<UserRepository> { UserRepositoryImpl(get(), get(), get(), get(), get(), get(), get()) }

    factory { GetUserUseCase(get()) }
    factory { GetUserBalanceUseCase(get()) }
    factory { SaveLocalUserUseCase(get()) }
    factory { GetAuthTokenUseCase(get()) }
}