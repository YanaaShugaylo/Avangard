package com.avangard.bratstvo.points_exchange.root.data.mapper

import com.avangard.bratstvo.points_exchange.root.data.model.PrizeDto
import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizeDtoMapper
import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizesListDtoMapper

class PrizesListDtoMapperImpl(private val prizeDtoMapper: PrizeDtoMapper) : PrizesListDtoMapper {

    override fun map(dtoList: List<PrizeDto>) = dtoList.map {
        prizeDtoMapper.map(it)
    }
}