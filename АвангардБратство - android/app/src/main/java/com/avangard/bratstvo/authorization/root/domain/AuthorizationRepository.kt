package com.avangard.bratstvo.authorization.root.domain

import com.avangard.bratstvo.authorization.root.domain.model.AuthorizationResult
import com.avangard.bratstvo.authorization.root.domain.model.UserCredentials

interface AuthorizationRepository {

    suspend fun authorizeUser(userCredentials: UserCredentials): AuthorizationResult
    suspend fun refreshAuthToken(refreshToken: String): Boolean
    fun logout(): Boolean
}