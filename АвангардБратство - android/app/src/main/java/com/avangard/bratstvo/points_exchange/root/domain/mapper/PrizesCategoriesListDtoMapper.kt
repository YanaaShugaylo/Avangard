package com.avangard.bratstvo.points_exchange.root.domain.mapper

import com.avangard.bratstvo.points_exchange.root.data.model.PrizesCategoryDto
import com.avangard.bratstvo.points_exchange.root.domain.model.PrizesCategory

interface PrizesCategoriesListDtoMapper {

    fun map(dtoList: List<PrizesCategoryDto>): List<PrizesCategory>
}