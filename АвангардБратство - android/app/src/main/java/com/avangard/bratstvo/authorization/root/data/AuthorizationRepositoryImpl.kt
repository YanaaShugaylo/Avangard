package com.avangard.bratstvo.authorization.root.data

import com.avangard.bratstvo.authorization.root.data.model.AuthTokensDto
import com.avangard.bratstvo.authorization.root.data.model.AuthUserDataDto
import com.avangard.bratstvo.authorization.root.data.model.RefreshTokenDto
import com.avangard.bratstvo.authorization.root.domain.AuthorizationLocalRepository
import com.avangard.bratstvo.authorization.root.domain.AuthorizationRepository
import com.avangard.bratstvo.authorization.root.domain.mapper.AuthorizationResultDtoMapper
import com.avangard.bratstvo.authorization.root.domain.mapper.UserCredentialsDtoMapper
import com.avangard.bratstvo.authorization.root.domain.model.UserCredentials
import com.avangard.bratstvo.base.coroutines.AppDispatchers
import com.avangard.bratstvo.base.exceptions.domain.AuthFailedException
import com.avangard.bratstvo.base.root.data.BaseBackendResponse
import kotlinx.coroutines.withContext
import java.lang.Exception

class AuthorizationRepositoryImpl(
    private val api: AuthorizationApi,
    private val authLocalRepository: AuthorizationLocalRepository,
    private val userCredentialsDtoMapper: UserCredentialsDtoMapper,
    private val authorizationResultDtoMapper: AuthorizationResultDtoMapper,
    private val appDispatchers: AppDispatchers
) : AuthorizationRepository {

    override suspend fun authorizeUser(userCredentials: UserCredentials) = withContext(appDispatchers.network) {
        var response: AuthUserDataDto?
        var errorMessage: String? = null

        try {
            response = api.authorizeUser(userCredentialsDtoMapper.map(userCredentials)).data

            authLocalRepository.saveTokens(response.authTokens)
        } catch (e: Exception) {
            response = null


            if (e is AuthFailedException) {
                errorMessage = "Неверный логин или пароль."
            }
        }

        val result = authorizationResultDtoMapper.map(response, errorMessage)

        if (!result.isFirstTime) {
            authLocalRepository.saveIsAuthorized()
        }

        result
    }

    override suspend fun refreshAuthToken(refreshToken: String) = withContext(appDispatchers.network) {
        var result: BaseBackendResponse<AuthTokensDto>? = null

        try {
            result = api.refreshAuthToken(RefreshTokenDto(refreshToken))
            authLocalRepository.saveTokens(result.data)
        } catch (e: Exception) {

        }

        result != null
    }

    override fun logout(): Boolean = authLocalRepository.removeAuthInfo()
}