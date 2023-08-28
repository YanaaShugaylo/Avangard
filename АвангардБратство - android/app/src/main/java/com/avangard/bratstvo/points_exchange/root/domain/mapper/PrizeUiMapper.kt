package com.avangard.bratstvo.points_exchange.root.domain.mapper

import com.avangard.bratstvo.points_exchange.root.domain.model.Prize
import com.avangard.bratstvo.points_exchange.root.presentation.model.PrizeUiModel

interface PrizeUiMapper {
    fun map(domainModel: Prize): PrizeUiModel
}