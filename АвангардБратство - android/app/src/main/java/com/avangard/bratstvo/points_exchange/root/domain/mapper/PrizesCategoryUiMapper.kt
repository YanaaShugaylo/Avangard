package com.avangard.bratstvo.points_exchange.root.domain.mapper

import com.avangard.bratstvo.points_exchange.root.domain.model.PrizesCategory
import com.avangard.bratstvo.points_exchange.root.presentation.model.PrizesCategoryUiModel

interface PrizesCategoryUiMapper {
    fun map(domainModel: PrizesCategory): PrizesCategoryUiModel
}