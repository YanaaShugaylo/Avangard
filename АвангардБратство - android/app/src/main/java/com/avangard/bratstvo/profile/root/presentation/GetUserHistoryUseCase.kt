package com.avangard.bratstvo.profile.root.presentation

import com.avangard.bratstvo.profile.root.domain.ProfileRepository
import com.avangard.bratstvo.profile.root.domain.model.UserSkill

class GetUserHistoryUseCase(private val profileRepository: ProfileRepository) {
    suspend operator fun invoke(): UserHistory = profileRepository.getHistory()
}

