package com.avangard.bratstvo.points_exchange.details.presentation.model

open class PrizeRewardUiModel {

    internal class Promocode(
        val code: String? = null,
        val qrCode: String? = null,
        val date: String,
        val isActive: Boolean
    ) : PrizeRewardUiModel()

    internal class QrCode(
        val qrCode: String
    ) : PrizeRewardUiModel()

    internal class Instruction(
        val title: String,
        val description: String
    ) : PrizeRewardUiModel()
}