package com.avangard.bratstvo.authorization.root.domain

class IsUserAuthorizedUseCase(private val authorizationLocalRepository: AuthorizationLocalRepository) {

    operator fun invoke(): Boolean = authorizationLocalRepository.isAuthorized()
}