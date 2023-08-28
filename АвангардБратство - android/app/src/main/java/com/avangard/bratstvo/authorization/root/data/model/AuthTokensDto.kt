package com.avangard.bratstvo.authorization.root.data.model

import com.google.gson.annotations.SerializedName

class AuthTokensDto(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("exp")
    val expirationDateMillis: Long
)