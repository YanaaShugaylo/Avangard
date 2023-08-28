package com.avangard.bratstvo.intro.interests.data.model

import com.google.gson.annotations.SerializedName

class InterestDto(
    val id: Int,
    @SerializedName("name")
    val title: String,
    @SerializedName("sort_order")
    val sortOrder: Int,
    @SerializedName("active")
    val isActive: Boolean
)