package com.avangard.bratstvo.authorization.root.data.model

import com.google.gson.annotations.SerializedName

class AuthUserDataDto(
    @SerializedName("user")
    val userSimpleData: UserSimpleDataDto,
    @SerializedName("auth")
    val authTokens: AuthTokensDto,
    @SerializedName("new_user")
    val isUserNew: Boolean
)