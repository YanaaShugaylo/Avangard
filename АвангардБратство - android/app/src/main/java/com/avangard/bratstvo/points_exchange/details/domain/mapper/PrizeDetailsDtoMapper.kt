package com.avangard.bratstvo.points_exchange.details.domain.mapper

import com.avangard.bratstvo.points_exchange.details.data.model.PrizeDetailsDto
import com.avangard.bratstvo.points_exchange.details.domain.model.PrizeDetails

interface PrizeDetailsDtoMapper {
    fun map(dto: PrizeDetailsDto): PrizeDetails
}