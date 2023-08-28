package com.avangard.bratstvo.points_exchange.root.data.model

import com.google.gson.annotations.SerializedName

class PrizeImageDto(
    val uuid: String,
    @SerializedName("name")
    val title: String,
    @SerializedName("file_name")
    val fileName: String,
    @SerializedName("preview_url")
    val previewUrl: String,
    @SerializedName("original_url")
    val originalUrl: String,
    @SerializedName("custom_properties")
    val customProperties: Array<Any?>,
    val extension: String,
    val size: Int
)