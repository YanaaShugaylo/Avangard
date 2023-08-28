package com.avangard.bratstvo.points_exchange.details.domain.mapper

import com.avangard.bratstvo.points_exchange.details.data.model.PrizeRewardDto
import com.avangard.bratstvo.points_exchange.details.domain.model.PrizeReward

interface PrizeRewardDtoMapper {
    fun map(dto: PrizeRewardDto): PrizeReward
}