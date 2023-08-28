package com.avangard.bratstvo.profile.root.data

import com.avangard.bratstvo.points_exchange.root.domain.model.PrizeBackground
import com.avangard.bratstvo.points_exchange.root.domain.model.PrizeImage
import com.google.gson.annotations.SerializedName

class MyPrize (
    val id: Int,
    val name: String,
    val price: Int,
    @SerializedName("created_at")
    val date: String


        )
