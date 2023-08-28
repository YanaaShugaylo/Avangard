package com.avangard.bratstvo.authorization.root.data.model

import com.google.gson.annotations.SerializedName

class RefreshTokenDto(
    @SerializedName("refresh_token")
    val refreshToken: String
)