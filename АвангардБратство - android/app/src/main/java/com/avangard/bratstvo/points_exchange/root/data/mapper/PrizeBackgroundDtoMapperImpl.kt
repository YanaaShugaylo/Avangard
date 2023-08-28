package com.avangard.bratstvo.points_exchange.root.data.mapper

import com.avangard.bratstvo.points_exchange.root.data.model.PrizeBackgroundDto
import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizeBackgroundDtoMapper
import com.avangard.bratstvo.points_exchange.root.domain.model.PrizeBackground

class PrizeBackgroundDtoMapperImpl : PrizeBackgroundDtoMapper {

    override fun map(dto: PrizeBackgroundDto) = PrizeBackground(
        dto.uuid,
        dto.title,
        dto.fileName,
        dto.previewUrl,
        dto.originalUrl,
        dto.customProperties,
        dto.extension,
        dto.size
    )
}