package com.avangard.bratstvo.profile.root.domain

import com.avangard.bratstvo.profile.root.domain.model.UserSkill

class GetUserSkillsUseCase(private val profileRepository: ProfileRepository) {
    suspend operator fun invoke(): List<UserSkill> = profileRepository.getSkills()
}