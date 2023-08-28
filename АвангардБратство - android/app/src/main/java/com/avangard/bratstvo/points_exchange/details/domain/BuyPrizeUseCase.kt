package com.avangard.bratstvo.points_exchange.details.domain

class BuyPrizeUseCase(private val repository: PointsExchangeDetailsRepository) {
    suspend operator fun invoke(prizeId: Int): Boolean = repository.buyPrize(prizeId)
}