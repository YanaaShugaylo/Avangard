package com.avangard.bratstvo.authorization.root.domain

class SaveIsAuthorizedUseCase(private val authorizationLocalRepository: AuthorizationLocalRepository) {

    suspend operator fun invoke() {
        authorizationLocalRepository.saveIsAuthorized()
    }
}