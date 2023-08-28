package com.avangard.bratstvo.points_exchange.details.domain.model

class PrizeReward(
    val type: PrizeRewardsTypes,
    val value: String,
    val boughtDate: String,
    val isActive: Boolean
)

enum class PrizeRewardsTypes {
    QR_CODE, PROMOCODE, NOTHING
}