package com.avangard.bratstvo.points_exchange.root.data.mapper

import com.avangard.bratstvo.points_exchange.root.data.model.PrizesCategoryDto
import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizesCategoriesListDtoMapper
import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizesCategoryDtoMapper

class PrizesCategoriesListDtoMapperImpl(
    private val prizesCategoryDtoMapper: PrizesCategoryDtoMapper
) : PrizesCategoriesListDtoMapper {

    override fun map(dtoList: List<PrizesCategoryDto>) = dtoList.map {
        prizesCategoryDtoMapper.map(it)
    }
}