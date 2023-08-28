package com.avangard.bratstvo.intro.interests.domain

import com.avangard.bratstvo.intro.interests.domain.model.Interest

class GetInterestsUseCase(private val aboutUserRepository: IntroInterestsRepository) {

    suspend operator fun invoke(): List<Interest> = aboutUserRepository.getInterests()
}