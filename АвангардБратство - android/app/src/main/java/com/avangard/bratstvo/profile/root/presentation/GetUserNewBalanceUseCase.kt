package com.avangard.bratstvo.profile.root.presentation

import com.avangard.bratstvo.profile.root.domain.ProfileRepository
import com.avangard.bratstvo.profile.root.domain.UserNewBalance
import com.avangard.bratstvo.profile.root.domain.model.UserRating

class GetUserNewBalanceUseCase(private val profileRepository: ProfileRepository) {

    suspend operator fun invoke(): UserNewBalance = profileRepository.getNewBalance()
}
