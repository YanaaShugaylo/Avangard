package com.avangard.bratstvo.authorization.root.domain

import com.avangard.bratstvo.authorization.root.domain.model.AuthorizationResult
import com.avangard.bratstvo.authorization.root.domain.model.UserCredentials

class AuthorizeUserUseCase(private val authorizationRepository: AuthorizationRepository) {

    suspend operator fun invoke(
        userCredentials: UserCredentials
    ): AuthorizationResult = authorizationRepository.authorizeUser(userCredentials)
}