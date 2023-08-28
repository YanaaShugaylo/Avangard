package com.avangard.bratstvo.points_exchange.root.domain.mapper

import com.avangard.bratstvo.points_exchange.root.data.model.PrizeBackgroundDto
import com.avangard.bratstvo.points_exchange.root.domain.model.PrizeBackground

interface PrizeBackgroundDtoMapper {

    fun map(dto: PrizeBackgroundDto): PrizeBackground
}