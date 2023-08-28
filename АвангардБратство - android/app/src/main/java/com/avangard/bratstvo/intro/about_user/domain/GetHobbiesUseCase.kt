package com.avangard.bratstvo.intro.about_user.domain

import com.avangard.bratstvo.intro.about_user.domain.model.Hobby

class GetHobbiesUseCase(private val aboutUserRepository: IntroAboutUserRepository) {

    suspend operator fun invoke(): List<Hobby> = aboutUserRepository.getHobbies()
}