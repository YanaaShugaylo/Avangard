package com.avangard.bratstvo.authorization.root.data

import com.avangard.bratstvo.authorization.root.data.model.AuthTokensDto
import com.avangard.bratstvo.authorization.root.domain.AuthorizationLocalRepository
import com.avangard.bratstvo.base.coroutines.AppDispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class AuthorizationLocalRepositoryImpl(
    private val localDataSource: AuthLocalDataSource,
    private val appDispatchers: AppDispatchers
) : AuthorizationLocalRepository {

    override fun getAuthToken() = localDataSource.getAuthToken()

    override fun isAuthorized() = localDataSource.getIsAuthorized()

    override suspend fun saveTokens(tokensDto: AuthTokensDto) {
        saveAuthToken(tokensDto.accessToken)
        saveRefreshToken(tokensDto.refreshToken, tokensDto.expirationDateMillis)
    }

    override suspend fun saveIsAuthorized() {
        withContext(appDispatchers.storage) {
            localDataSource.saveIsAuthorized()
        }
    }

    override fun removeAuthInfo(): Boolean {
        return try {
            removeAuthToken()
            removeRefreshToken()
            removeIsAuthorized()
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun removeIsAuthorized() {
        localDataSource.removeIsAuthorized()
    }

    private suspend fun saveAuthToken(authToken: String) {
        withContext(appDispatchers.storage) {
            localDataSource.saveAuthToken(authToken)
        }
    }

    private suspend fun saveRefreshToken(refreshToken: String, refreshTokenExpired: Long) {
        withContext(appDispatchers.storage) {
            localDataSource.saveRefreshToken(refreshToken)
        }
    }

    private fun removeAuthToken() {
        localDataSource.removeAuthToken()
    }

    private fun removeRefreshToken() {
        localDataSource.removeRefreshToken()
    }
}