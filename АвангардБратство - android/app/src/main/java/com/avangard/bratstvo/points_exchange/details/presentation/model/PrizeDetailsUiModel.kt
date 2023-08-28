package com.avangard.bratstvo.points_exchange.details.presentation.model

class PrizeDetailsUiModel(
    val id: Int,
    val title: String,
    val price: Int,
    val isActive: Boolean,
    val endDate: String?,
    val description: String,
    val backColor: String?,
    val backgroundImageLink: String,
    val imageLink: String,
    val rewards: List<PrizeRewardUiModel>
)