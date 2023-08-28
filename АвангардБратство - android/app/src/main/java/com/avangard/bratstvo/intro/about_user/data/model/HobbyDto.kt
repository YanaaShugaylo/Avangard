package com.avangard.bratstvo.intro.about_user.data.model

import com.google.gson.annotations.SerializedName

class HobbyDto(
    val id: Int,
    @SerializedName("name")
    val title: String,
    @SerializedName("sort_order")
    val sortOrder: Int,
    @SerializedName("active")
    val isActive: Boolean
)