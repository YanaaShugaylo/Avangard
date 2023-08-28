package com.avangard.bratstvo.points_exchange.details.domain.mapper

import com.avangard.bratstvo.points_exchange.details.domain.model.PrizeRewardsTypes

interface PrizeRewardTypeDtoMapper {

    fun map(dto: String): PrizeRewardsTypes
}