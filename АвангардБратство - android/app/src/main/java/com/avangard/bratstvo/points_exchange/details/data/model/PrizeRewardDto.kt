package com.avangard.bratstvo.points_exchange.details.data.model

import com.google.gson.annotations.SerializedName

class PrizeRewardDto(
    val type: String,
    val value: String,
    @SerializedName("bought_at")
    val boughtDate: String,
    val active: Boolean
)