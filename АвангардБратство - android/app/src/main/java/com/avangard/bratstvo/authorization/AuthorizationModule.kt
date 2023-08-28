package com.avangard.bratstvo.authorization

import com.avangard.bratstvo.authorization.root.data.AuthorizationApi
import com.avangard.bratstvo.authorization.root.data.AuthorizationLocalRepositoryImpl
import com.avangard.bratstvo.authorization.root.data.AuthorizationRepositoryImpl
import com.avangard.bratstvo.authorization.root.data.mapper.AuthorizationResultDtoMapperImpl
import com.avangard.bratstvo.authorization.root.data.mapper.AuthorizationResultUiMapperImpl
import com.avangard.bratstvo.authorization.root.data.mapper.UserCredentialsDtoMapperImpl
import com.avangard.bratstvo.authorization.root.domain.AuthorizationLocalRepository
import com.avangard.bratstvo.authorization.root.domain.AuthorizationRepository
import com.avangard.bratstvo.authorization.root.domain.AuthorizeUserUseCase
import com.avangard.bratstvo.authorization.root.domain.GetAuthTokenUseCase
import com.avangard.bratstvo.authorization.root.domain.IsUserAuthorizedUseCase
import com.avangard.bratstvo.authorization.root.domain.SaveIsAuthorizedUseCase
import com.avangard.bratstvo.authorization.root.domain.mapper.AuthorizationResultDtoMapper
import com.avangard.bratstvo.authorization.root.domain.mapper.AuthorizationResultUiMapper
import com.avangard.bratstvo.authorization.root.domain.mapper.UserCredentialsDtoMapper
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

fun authorizationModule() = module {
    factory<AuthorizationApi> { get<Retrofit>().create() }

    factory<UserCredentialsDtoMapper> { UserCredentialsDtoMapperImpl() }

    factory<AuthorizationRepository> { AuthorizationRepositoryImpl(get(), get(), get(), get(), get()) }
    factory<AuthorizationLocalRepository> { AuthorizationLocalRepositoryImpl(get(), get()) }
    factory<AuthorizationResultDtoMapper> { AuthorizationResultDtoMapperImpl() }
    factory<AuthorizationResultUiMapper> { AuthorizationResultUiMapperImpl() }

    factory { AuthorizeUserUseCase(get()) }
    factory { GetAuthTokenUseCase(get()) }
    factory { IsUserAuthorizedUseCase(get()) }
    factory { SaveIsAuthorizedUseCase(get()) }
}