package com.avangard.bratstvo.points_exchange.details.domain

import com.avangard.bratstvo.points_exchange.details.domain.model.PrizeDetails

class GetPrizeDetailsUseCase(private val repository: PointsExchangeDetailsRepository) {

    suspend operator fun invoke(prizeId: Int): PrizeDetails? = repository.getPrizeDetails(prizeId)
}