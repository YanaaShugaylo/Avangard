package com.avangard.bratstvo.points_exchange.root.domain

class GetPrizesCategoriesUseCase(private val pointsExchangeRepository: PointsExchangeRepository) {

    suspend operator fun invoke() = pointsExchangeRepository.getPrizesCategories()
}