package com.avangard.bratstvo.points_exchange.details.data.model

import com.avangard.bratstvo.tasks.root.data.model.ImageAttachmentDto
import com.google.gson.annotations.SerializedName

class PrizeDetailsDto(
    val id: Int,
    @SerializedName("name")
    val title: String,
    val price: Int,
    @SerializedName("active")
    val isActive: Boolean,
    @SerializedName("end_date")
    val endDate: String?,
    val description: String,
    @SerializedName("reward_description")
    val rewardDescription: String?,
    @SerializedName("back_color")
    val backColor: String?,
    @SerializedName("background")
    val backgroundImage: ImageAttachmentDto,
    val image: ImageAttachmentDto,
    val rewards: List<PrizeRewardDto>
)