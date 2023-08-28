package com.avangard.bratstvo.points_exchange.root.domain

import com.avangard.bratstvo.points_exchange.root.domain.model.PrizesCategory
import com.avangard.bratstvo.points_exchange.root.domain.model.PrizesList

interface PointsExchangeRepository {

    suspend fun getPrizesCategories(): List<PrizesCategory>
    suspend fun getPrizesAll(page: Int?): PrizesList
    suspend fun getPrizes(categoryId: Int, page: Int?): PrizesList
    suspend fun getPrizesBought(page: Int?): PrizesList
}