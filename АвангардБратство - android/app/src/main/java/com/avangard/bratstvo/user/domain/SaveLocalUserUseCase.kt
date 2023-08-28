package com.avangard.bratstvo.user.domain

import com.avangard.bratstvo.user.domain.model.User

class SaveLocalUserUseCase(private val userRepository: UserRepository) {

    suspend operator fun invoke(user: User) {
        userRepository.saveLocal(user)
    }
}