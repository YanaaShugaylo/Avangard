package com.avangard.bratstvo.authorization.root.domain

class GetAuthTokenUseCase(private val authorizationLocalRepository: AuthorizationLocalRepository) {

    operator fun invoke(): String? = authorizationLocalRepository.getAuthToken()
}