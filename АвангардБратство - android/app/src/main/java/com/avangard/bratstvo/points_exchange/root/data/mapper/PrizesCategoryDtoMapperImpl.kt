package com.avangard.bratstvo.points_exchange.root.data.mapper

import com.avangard.bratstvo.points_exchange.root.data.model.PrizesCategoryDto
import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizesCategoryDtoMapper
import com.avangard.bratstvo.points_exchange.root.domain.model.PrizesCategory

class PrizesCategoryDtoMapperImpl : PrizesCategoryDtoMapper {

    override fun map(dto: PrizesCategoryDto) = PrizesCategory(
        dto.id,
        dto.title
    )
}