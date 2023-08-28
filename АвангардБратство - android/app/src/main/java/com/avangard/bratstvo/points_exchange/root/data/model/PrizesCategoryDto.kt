package com.avangard.bratstvo.points_exchange.root.data.model

import com.google.gson.annotations.SerializedName

class PrizesCategoryDto(
    val id: Int,
    @SerializedName("name")
    val title: String
)