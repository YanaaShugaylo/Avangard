package com.avangard.bratstvo.points_exchange.root.domain

import com.avangard.bratstvo.points_exchange.root.domain.model.PrizesList

class GetPrizesUseCase(private val pointsExchangeRepository: PointsExchangeRepository) {

    suspend operator fun invoke(
        categoryId: Int? = null,
        getBought: Boolean? = null,
        page: Int? = null
    ): PrizesList = when {
        categoryId != null -> pointsExchangeRepository.getPrizes(categoryId, page)
        getBought == true -> pointsExchangeRepository.getPrizesBought(page)
        else -> pointsExchangeRepository.getPrizesAll(page)
    }
}