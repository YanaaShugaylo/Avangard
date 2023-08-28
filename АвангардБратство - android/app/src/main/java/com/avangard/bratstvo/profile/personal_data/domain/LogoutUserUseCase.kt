package com.avangard.bratstvo.profile.personal_data.domain

import com.avangard.bratstvo.authorization.root.domain.AuthorizationRepository

class LogoutUserUseCase(private val repository: AuthorizationRepository) {

    operator fun invoke(): Boolean  = repository.logout()
}