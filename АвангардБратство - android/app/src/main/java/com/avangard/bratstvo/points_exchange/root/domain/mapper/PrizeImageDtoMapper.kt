package com.avangard.bratstvo.points_exchange.root.domain.mapper

import com.avangard.bratstvo.points_exchange.root.data.model.PrizeImageDto
import com.avangard.bratstvo.points_exchange.root.domain.model.PrizeImage

interface PrizeImageDtoMapper {

    fun map(dto: PrizeImageDto): PrizeImage
}