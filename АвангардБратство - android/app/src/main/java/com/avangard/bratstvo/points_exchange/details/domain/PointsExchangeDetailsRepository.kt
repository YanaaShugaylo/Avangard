package com.avangard.bratstvo.points_exchange.details.domain

import com.avangard.bratstvo.points_exchange.details.domain.model.PrizeDetails

interface PointsExchangeDetailsRepository {

    suspend fun getPrizeDetails(prizeId: Int): PrizeDetails?
    suspend fun buyPrize(prizeId: Int): Boolean
}