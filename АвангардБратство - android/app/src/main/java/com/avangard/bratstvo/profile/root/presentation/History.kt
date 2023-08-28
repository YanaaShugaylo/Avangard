package com.avangard.bratstvo.profile.root.presentation

import com.avangard.bratstvo.points_exchange.root.domain.model.Prize
import com.avangard.bratstvo.profile.root.data.MyPrize
import com.google.gson.annotations.SerializedName

class History (
    val id: Int,
    val type: String,
    val prize: MyPrize,
    val image: ImageHistory,
    @SerializedName("bought_at")
    val bought: String
        )
