package com.avangard.bratstvo.points_exchange.details.domain.mapper

import com.avangard.bratstvo.points_exchange.details.domain.model.PrizeDetails
import com.avangard.bratstvo.points_exchange.details.presentation.model.PrizeDetailsUiModel

interface PrizeDetailsUiMapper {
    fun map(domainModel: PrizeDetails?): PrizeDetailsUiModel?
}