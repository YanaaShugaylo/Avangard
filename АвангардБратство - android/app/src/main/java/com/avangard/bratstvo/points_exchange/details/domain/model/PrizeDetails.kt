package com.avangard.bratstvo.points_exchange.details.domain.model

import com.avangard.bratstvo.tasks.root.domain.model.ImageAttachment

class PrizeDetails(
    val id: Int,
    val title: String,
    val price: Int,
    val isActive: Boolean,
    val endDate: String?,
    val description: String,
    val rewardDescription: String?,
    val backColor: String?,
    val backgroundImage: ImageAttachment?,
    val image: ImageAttachment?,
    val rewards: List<PrizeReward>
)