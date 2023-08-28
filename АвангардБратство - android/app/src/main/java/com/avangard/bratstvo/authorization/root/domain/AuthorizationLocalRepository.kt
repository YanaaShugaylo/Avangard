package com.avangard.bratstvo.authorization.root.domain

import com.avangard.bratstvo.authorization.root.data.model.AuthTokensDto

interface AuthorizationLocalRepository {

    fun getAuthToken(): String?
    fun isAuthorized(): Boolean
    suspend fun saveTokens(tokensDto: AuthTokensDto)
    suspend fun saveIsAuthorized()
    fun removeAuthInfo(): Boolean
    fun removeIsAuthorized()
}