package com.avangard.bratstvo.points_exchange.details.domain.mapper

import com.avangard.bratstvo.points_exchange.details.domain.model.PrizeReward
import com.avangard.bratstvo.points_exchange.details.presentation.model.PrizeRewardUiModel

interface PrizeRewardUiMapper {
    fun map(domainModel: PrizeReward, oneCode: Boolean = false): PrizeRewardUiModel
}