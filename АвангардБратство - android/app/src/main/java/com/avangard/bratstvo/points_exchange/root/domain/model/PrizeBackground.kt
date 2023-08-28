package com.avangard.bratstvo.points_exchange.root.domain.model

import com.google.gson.annotations.SerializedName

class PrizeBackground(
    val uuid: String,
    val title: String,
    val fileName: String,
    val previewUrl: String,
    val originalUrl: String,
    val customProperties: Array<Any?>,
    val extension: String,
    val size: Int
)