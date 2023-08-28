package com.avangard.bratstvo.points_exchange.details

import com.avangard.bratstvo.points_exchange.details.data.PointsExchangeDetailsApi
import com.avangard.bratstvo.points_exchange.details.data.PointsExchangeDetailsRepositoryImpl
import com.avangard.bratstvo.points_exchange.details.data.mapper.PrizeDetailsDtoMapperImpl
import com.avangard.bratstvo.points_exchange.details.data.mapper.PrizeDetailsUiMapperImpl
import com.avangard.bratstvo.points_exchange.details.data.mapper.PrizeRewardDtoMapperImpl
import com.avangard.bratstvo.points_exchange.details.data.mapper.PrizeRewardTypeDtoMapperImpl
import com.avangard.bratstvo.points_exchange.details.data.mapper.PrizeRewardUiMapperImpl
import com.avangard.bratstvo.points_exchange.details.domain.BuyPrizeUseCase
import com.avangard.bratstvo.points_exchange.details.domain.GetPrizeDetailsUseCase
import com.avangard.bratstvo.points_exchange.details.domain.PointsExchangeDetailsRepository
import com.avangard.bratstvo.points_exchange.details.domain.mapper.PrizeDetailsDtoMapper
import com.avangard.bratstvo.points_exchange.details.domain.mapper.PrizeDetailsUiMapper
import com.avangard.bratstvo.points_exchange.details.domain.mapper.PrizeRewardDtoMapper
import com.avangard.bratstvo.points_exchange.details.domain.mapper.PrizeRewardTypeDtoMapper
import com.avangard.bratstvo.points_exchange.details.domain.mapper.PrizeRewardUiMapper
import com.avangard.bratstvo.points_exchange.details.presentation.PointsExchangeDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

internal fun pointsExchangeDetailsModule() = module {
    factory<PointsExchangeDetailsApi> { get<Retrofit>().create() }

    factory<PrizeDetailsDtoMapper> { PrizeDetailsDtoMapperImpl(get(), get()) }
    factory<PrizeRewardDtoMapper> { PrizeRewardDtoMapperImpl(get()) }
    factory<PrizeRewardTypeDtoMapper> { PrizeRewardTypeDtoMapperImpl() }
    factory<PrizeDetailsUiMapper> { PrizeDetailsUiMapperImpl(get()) }
    factory<PrizeRewardUiMapper> { PrizeRewardUiMapperImpl() }

    factory<PointsExchangeDetailsRepository> { PointsExchangeDetailsRepositoryImpl(get(), get(), get()) }

    factory { GetPrizeDetailsUseCase(get()) }
    factory { BuyPrizeUseCase(get()) }

    viewModel { PointsExchangeDetailsViewModel(get(), get(), get(), get()) }
}