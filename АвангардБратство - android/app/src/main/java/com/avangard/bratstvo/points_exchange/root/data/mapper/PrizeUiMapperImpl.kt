package com.avangard.bratstvo.points_exchange.root.data.mapper

import com.avangard.bratstvo.base.extensions.domain.string.getDateFromUtc
import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizeUiMapper
import com.avangard.bratstvo.points_exchange.root.domain.model.Prize
import com.avangard.bratstvo.points_exchange.root.presentation.model.PrizeUiModel

class PrizeUiMapperImpl : PrizeUiMapper {

    override fun map(domainModel: Prize) = PrizeUiModel(
        domainModel.id,
        domainModel.title,
        domainModel.image.previewUrl,
        domainModel.price,
        domainModel.endDate?.getDateFromUtc(),
        if (domainModel.boughtCount > 0) domainModel.boughtCount else null
    )
}