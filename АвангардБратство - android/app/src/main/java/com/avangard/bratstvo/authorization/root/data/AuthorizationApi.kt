package com.avangard.bratstvo.authorization.root.data

import com.avangard.bratstvo.authorization.root.data.model.AuthTokensDto
import com.avangard.bratstvo.authorization.root.data.model.AuthUserDataDto
import com.avangard.bratstvo.authorization.root.data.model.RefreshTokenDto
import com.avangard.bratstvo.authorization.root.data.model.UserCredentialsDto
import com.avangard.bratstvo.base.root.data.BaseBackendResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizationApi {

    @POST("/api/v1/auth/login")
    suspend fun authorizeUser(
        @Body userCredentials: UserCredentialsDto
    ): BaseBackendResponse<AuthUserDataDto>

    @POST("/api/v1/auth/tokens/refresh")
    suspend fun refreshAuthToken(
        @Body refreshToken: RefreshTokenDto
    ): BaseBackendResponse<AuthTokensDto>
}