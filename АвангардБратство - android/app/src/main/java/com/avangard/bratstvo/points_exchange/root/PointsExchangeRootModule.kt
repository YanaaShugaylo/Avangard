package com.avangard.bratstvo.points_exchange.root

import com.avangard.bratstvo.points_exchange.root.data.PointsExchangeApi
import com.avangard.bratstvo.points_exchange.root.data.PointsExchangeRepositoryImpl
import com.avangard.bratstvo.points_exchange.root.data.mapper.PrizeBackgroundDtoMapperImpl
import com.avangard.bratstvo.points_exchange.root.data.mapper.PrizeDtoMapperImpl
import com.avangard.bratstvo.points_exchange.root.data.mapper.PrizeImageDtoMapperImpl
import com.avangard.bratstvo.points_exchange.root.data.mapper.PrizeUiMapperImpl
import com.avangard.bratstvo.points_exchange.root.data.mapper.PrizesCategoriesListDtoMapperImpl
import com.avangard.bratstvo.points_exchange.root.data.mapper.PrizesCategoryDtoMapperImpl
import com.avangard.bratstvo.points_exchange.root.data.mapper.PrizesCategoryUiMapperImpl
import com.avangard.bratstvo.points_exchange.root.data.mapper.PrizesListDtoMapperImpl
import com.avangard.bratstvo.points_exchange.root.domain.GetPrizesCategoriesUseCase
import com.avangard.bratstvo.points_exchange.root.domain.GetPrizesUseCase
import com.avangard.bratstvo.points_exchange.root.domain.PointsExchangeRepository
import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizeBackgroundDtoMapper
import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizeDtoMapper
import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizeImageDtoMapper
import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizeUiMapper
import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizesCategoriesListDtoMapper
import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizesCategoryDtoMapper
import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizesCategoryUiMapper
import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizesListDtoMapper
import com.avangard.bratstvo.points_exchange.root.presentation.PointsExchangeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

internal fun pointsExchangeRootModule() = module {
    factory<PointsExchangeApi> { get<Retrofit>().create() }

    factory<PrizesCategoriesListDtoMapper> { PrizesCategoriesListDtoMapperImpl(get()) }
    factory<PrizesCategoryDtoMapper> { PrizesCategoryDtoMapperImpl() }
    factory<PrizesListDtoMapper> { PrizesListDtoMapperImpl(get()) }
    factory<PrizeDtoMapper> { PrizeDtoMapperImpl(get(), get()) }
    factory<PrizeImageDtoMapper> { PrizeImageDtoMapperImpl() }
    factory<PrizeBackgroundDtoMapper> { PrizeBackgroundDtoMapperImpl() }
    factory<PrizeUiMapper> { PrizeUiMapperImpl() }
    factory<PrizesCategoryUiMapper> { PrizesCategoryUiMapperImpl() }

    factory<PointsExchangeRepository> { PointsExchangeRepositoryImpl(get(), get(), get(), get()) }

    factory { GetPrizesCategoriesUseCase(get()) }
    factory { GetPrizesUseCase(get()) }

    viewModel { PointsExchangeViewModel(get(), get(), get(), get()) }
}