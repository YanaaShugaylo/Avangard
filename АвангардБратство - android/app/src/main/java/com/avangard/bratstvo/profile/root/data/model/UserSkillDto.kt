package com.avangard.bratstvo.profile.root.data.model

import com.google.gson.annotations.SerializedName

class UserSkillDto(
    @SerializedName("name")
    val title: String,
    val total: Int,
    val progress: Int
)