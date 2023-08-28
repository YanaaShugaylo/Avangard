package com.avangard.bratstvo.points_exchange.root.data

import com.avangard.bratstvo.base.coroutines.AppDispatchers
import com.avangard.bratstvo.points_exchange.root.domain.PointsExchangeRepository
import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizesCategoriesListDtoMapper
import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizesListDtoMapper
import com.avangard.bratstvo.points_exchange.root.domain.model.Prize
import com.avangard.bratstvo.points_exchange.root.domain.model.PrizesList
import kotlinx.coroutines.withContext

class PointsExchangeRepositoryImpl(
    private val api: PointsExchangeApi,
    private val prizesCategoriesListDtoMapper: PrizesCategoriesListDtoMapper,
    private val prizesListDtoMapper: PrizesListDtoMapper,
    private val appDispatchers: AppDispatchers
) : PointsExchangeRepository {

    override suspend fun getPrizesCategories() = withContext(appDispatchers.network) {
        prizesCategoriesListDtoMapper.map(api.getPrizesCategories().data)
    }

    override suspend fun getPrizesAll(page: Int?) = withContext(appDispatchers.network) {
        val result = api.getPrizes(page = page)
        PrizesList(
            prizesListDtoMapper.map(result.data),
            isLastPage = result.pagination.currentPage == result.pagination.totalPages
        )
    }

    override suspend fun getPrizes(categoryId: Int, page: Int?) = withContext(appDispatchers.network) {
        val result = api.getPrizes(categoryId, page = page)
        PrizesList(
            prizesListDtoMapper.map(result.data),
            isLastPage = result.pagination.currentPage == result.pagination.totalPages
        )
    }

    override suspend fun getPrizesBought(page: Int?) = withContext(appDispatchers.network) {
        val result = api.getPrizes(getBought = 1, page = page)
        PrizesList(
            prizesListDtoMapper.map(result.data),
            isLastPage = result.pagination.currentPage == result.pagination.totalPages
        )
    }
}