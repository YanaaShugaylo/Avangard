package com.avangard.bratstvo.points_exchange.root.domain.mapper

import com.avangard.bratstvo.points_exchange.root.data.model.PrizeDto
import com.avangard.bratstvo.points_exchange.root.domain.model.Prize

interface PrizeDtoMapper {

    fun map(dto: PrizeDto): Prize
}