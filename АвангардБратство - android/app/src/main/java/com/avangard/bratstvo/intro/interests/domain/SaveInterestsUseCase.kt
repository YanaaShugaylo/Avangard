package com.avangard.bratstvo.intro.interests.domain

import com.avangard.bratstvo.intro.interests.domain.model.IntroSecondStepData

class SaveInterestsUseCase(private val aboutUserRepository: IntroInterestsRepository) {

    suspend operator fun invoke(data: IntroSecondStepData): Boolean = aboutUserRepository.saveInterests(data)
}