package com.avangard.bratstvo.points_exchange.root.data.model

import com.google.gson.annotations.SerializedName

class PrizeDto(
    val id: Int,
    @SerializedName("name")
    val title: String,
    val description: String,
    @SerializedName("end_date")
    val endDate: String?,
    val price: Int,
    @SerializedName("bought")
    val boughtCount: Int,
    @SerializedName("back_color")
    val backColor: String?,
    val image: PrizeImageDto,
    val background: PrizeBackgroundDto
)