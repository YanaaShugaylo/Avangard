package com.avangard.bratstvo.points_exchange.details.data.mapper

import com.avangard.bratstvo.points_exchange.details.domain.mapper.PrizeRewardTypeDtoMapper
import com.avangard.bratstvo.points_exchange.details.domain.model.PrizeRewardsTypes

class PrizeRewardTypeDtoMapperImpl : PrizeRewardTypeDtoMapper {

    override fun map(dto: String) = when(dto) {
        "qr" -> PrizeRewardsTypes.QR_CODE
        "promo_code", "code_word" -> PrizeRewardsTypes.PROMOCODE
        else -> PrizeRewardsTypes.NOTHING
    }
}