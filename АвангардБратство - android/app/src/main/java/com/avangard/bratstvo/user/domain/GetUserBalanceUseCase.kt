package com.avangard.bratstvo.user.domain

class GetUserBalanceUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): Int = userRepository.getBalance().count
}