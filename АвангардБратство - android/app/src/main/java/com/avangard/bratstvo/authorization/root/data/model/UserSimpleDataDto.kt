package com.avangard.bratstvo.authorization.root.data.model

import com.google.gson.annotations.SerializedName

class UserSimpleDataDto(
    val id: Int,
    val login: String,
    @SerializedName("full_name")
    val fullName: String
)