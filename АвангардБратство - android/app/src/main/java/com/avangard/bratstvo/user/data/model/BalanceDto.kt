package com.avangard.bratstvo.user.data.model

import com.google.gson.annotations.SerializedName

class BalanceDto(
    @SerializedName("balance")
    val count: Int
)