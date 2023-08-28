package com.avangard.bratstvo.user.domain

import com.avangard.bratstvo.user.domain.model.User

class GetUserUseCase(private val userRepository: UserRepository) {

    suspend operator fun invoke(): User = userRepository.getUser()
}