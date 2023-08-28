package com.avangard.bratstvo.points_exchange.root.presentation.model

class PrizeUiModel(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val points: Int,
    val endDate: String?,
    val multiplier: Int?
)