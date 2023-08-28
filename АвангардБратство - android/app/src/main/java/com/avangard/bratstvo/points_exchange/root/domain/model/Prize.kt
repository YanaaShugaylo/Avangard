package com.avangard.bratstvo.points_exchange.root.domain.model

class Prize(
    val id: Int,
    val title: String,
    val description: String,
    val endDate: String?,
    val price: Int,
    val boughtCount: Int,
    val backColor: String?,
    val image: PrizeImage,
    val background: PrizeBackground
)