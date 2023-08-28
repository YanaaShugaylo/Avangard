package com.avangard.bratstvo.points_exchange.root.data.mapper

import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizesCategoryUiMapper
import com.avangard.bratstvo.points_exchange.root.domain.model.PrizesCategory
import com.avangard.bratstvo.points_exchange.root.presentation.model.PrizesCategoryUiModel

class PrizesCategoryUiMapperImpl : PrizesCategoryUiMapper {

    override fun map(domainModel: PrizesCategory) = PrizesCategoryUiModel(
        domainModel.id,
        domainModel.title
    )
}