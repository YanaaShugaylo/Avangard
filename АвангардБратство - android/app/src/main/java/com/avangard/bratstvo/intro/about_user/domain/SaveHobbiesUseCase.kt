package com.avangard.bratstvo.intro.about_user.domain

import com.avangard.bratstvo.intro.about_user.domain.model.IntroFirstStepData

class SaveHobbiesUseCase(private val aboutUserRepository: IntroAboutUserRepository) {

    suspend operator fun invoke(data: IntroFirstStepData): Boolean = aboutUserRepository.saveHobbies(data)
}