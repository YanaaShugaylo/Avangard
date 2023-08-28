package com.avangard.bratstvo.points_exchange.root.data.mapper

import com.avangard.bratstvo.points_exchange.root.data.model.PrizeDto
import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizeBackgroundDtoMapper
import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizeDtoMapper
import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizeImageDtoMapper
import com.avangard.bratstvo.points_exchange.root.domain.model.Prize

class PrizeDtoMapperImpl(
    private val prizeImageDtoMapper: PrizeImageDtoMapper,
    private val prizeBackgroundDtoMapper: PrizeBackgroundDtoMapper
) : PrizeDtoMapper {

    override fun map(dto: PrizeDto) = Prize(
        dto.id,
        dto.title,
        dto.description,
        dto.endDate,
        dto.price,
        dto.boughtCount,
        dto.backColor,
        prizeImageDtoMapper.map(dto.image),
        prizeBackgroundDtoMapper.map(dto.background)
    )
}