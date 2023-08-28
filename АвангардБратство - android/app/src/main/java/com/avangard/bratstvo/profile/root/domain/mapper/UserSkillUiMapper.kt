package com.avangard.bratstvo.profile.root.domain.mapper

import com.avangard.bratstvo.profile.root.domain.model.UserSkill
import com.avangard.bratstvo.profile.root.presentation.model.UserSkillUiModel

interface UserSkillUiMapper {

    fun map(domainModel: UserSkill): UserSkillUiModel
}