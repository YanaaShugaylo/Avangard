package com.avangard.bratstvo.points_exchange.details.data.mapper

import com.avangard.bratstvo.base.extensions.domain.string.getDateFromUtc
import com.avangard.bratstvo.points_exchange.details.data.model.PrizeRewardDto
import com.avangard.bratstvo.points_exchange.details.domain.mapper.PrizeRewardDtoMapper
import com.avangard.bratstvo.points_exchange.details.domain.mapper.PrizeRewardTypeDtoMapper
import com.avangard.bratstvo.points_exchange.details.domain.model.PrizeReward

class PrizeRewardDtoMapperImpl(private val prizeRewardTypeDtoMapper: PrizeRewardTypeDtoMapper) : PrizeRewardDtoMapper {

    override fun map(dto: PrizeRewardDto) = PrizeReward(
        prizeRewardTypeDtoMapper.map(dto.type),
        dto.value,
        dto.boughtDate.getDateFromUtc(),
        dto.active
    )
}