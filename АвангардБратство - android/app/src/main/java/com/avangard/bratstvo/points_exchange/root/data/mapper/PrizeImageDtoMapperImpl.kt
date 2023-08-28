package com.avangard.bratstvo.points_exchange.root.data.mapper

import com.avangard.bratstvo.points_exchange.root.data.model.PrizeImageDto
import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizeImageDtoMapper
import com.avangard.bratstvo.points_exchange.root.domain.model.PrizeImage

class PrizeImageDtoMapperImpl : PrizeImageDtoMapper {

    override fun map(dto: PrizeImageDto) = PrizeImage(
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