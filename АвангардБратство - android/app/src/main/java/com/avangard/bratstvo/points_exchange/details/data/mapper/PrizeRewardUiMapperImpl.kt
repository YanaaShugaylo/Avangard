package com.avangard.bratstvo.points_exchange.details.data.mapper

import com.avangard.bratstvo.points_exchange.details.domain.mapper.PrizeRewardUiMapper
import com.avangard.bratstvo.points_exchange.details.domain.model.PrizeReward
import com.avangard.bratstvo.points_exchange.details.domain.model.PrizeRewardsTypes
import com.avangard.bratstvo.points_exchange.details.presentation.model.PrizeRewardUiModel

class PrizeRewardUiMapperImpl : PrizeRewardUiMapper {

    override fun map(domainModel: PrizeReward, oneCode: Boolean) = when(domainModel.type) {
        PrizeRewardsTypes.QR_CODE-> {
            if (oneCode) {
                PrizeRewardUiModel.QrCode(domainModel.value)
            } else {
                PrizeRewardUiModel.Promocode(
                    qrCode = domainModel.value,
                    date = domainModel.boughtDate,
                    isActive = domainModel.isActive
                )
            }
        }
        PrizeRewardsTypes.PROMOCODE -> {
            PrizeRewardUiModel.Promocode(
                code = domainModel.value,
                date = domainModel.boughtDate,
                isActive = domainModel.isActive
            )
        }
        else -> PrizeRewardUiModel.Instruction("Как воспользоваться", domainModel.value)
    }
}